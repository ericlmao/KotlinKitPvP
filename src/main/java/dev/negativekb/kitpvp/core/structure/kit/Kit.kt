package dev.negativekb.kitpvp.core.structure.kit

import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.inventory.ItemStack
import dev.negativekb.api.util.UtilPlayer
import org.bukkit.inventory.PlayerInventory
import java.util.function.BiConsumer
import org.bukkit.Sound
import dev.negativekb.kitpvp.core.structure.kit.KitInfo
import java.lang.IllegalStateException
import java.util.*
import java.util.function.Consumer
import java.util.function.Function

abstract class Kit {
    private var damagePlayerEventConsumer: Consumer<EntityDamageByEntityEvent>? = null
    private var deathEventConsumer: Consumer<PlayerDeathEvent>? = null
    private var moveEventConsumer: Consumer<PlayerMoveEvent>? = null
    private var interactEventConsumer: Consumer<PlayerInteractEvent>? = null
    private var leftClickEventConsumer: Consumer<PlayerInteractEvent>? = null
    private var rightClickEventConsumer: Consumer<PlayerInteractEvent>? = null
    private var rightClickEntityEventConsumer: Consumer<PlayerInteractAtEntityEvent>? = null
    private var potionEffects: Function<Player, Collection<PotionEffect>>? = null

    private val name: String
    private val cost: Long

    /**
     * @return - Returns helmet itemstack
     */
    abstract val helmet: ItemStack?

    /**
     * @return - Returns chestplate itemstack
     */
    abstract val chestplate: ItemStack?

    /**
     * @return - Returns leggings itemstack
     */
    abstract val leggings: ItemStack?

    /**
     * @return - Returns boots itemstack
     */
    abstract val boots: ItemStack?

    /**
     * @return - Returns hashmap of kit contents, Integer is the index of the soon-to-be inventory
     */
    abstract fun kitContents(): HashMap<Int, ItemStack>

    /**
     * @return - Returns icon of the kit for the Kit Selector
     */
    abstract val icon: ItemStack?

    fun applyKit(player: Player) {
        UtilPlayer.reset(player)
        val inv = player.inventory

        Optional.ofNullable(helmet).ifPresent { helmet: ItemStack? -> inv.helmet = helmet }
        Optional.ofNullable(chestplate).ifPresent { chestplate: ItemStack? -> inv.chestplate = chestplate }
        Optional.ofNullable(leggings).ifPresent { leggings: ItemStack? -> inv.leggings = leggings }
        Optional.ofNullable(boots).ifPresent { boots: ItemStack? -> inv.boots = boots }

        Optional.ofNullable(kitContents()).ifPresent { contents: HashMap<Int, ItemStack> ->
            contents.forEach { (index: Int?, item: ItemStack?) -> inv.setItem(index, item) } }

        Optional.ofNullable(potionEffects).ifPresent { function: Function<Player, Collection<PotionEffect>>?
            -> function!!.apply(player).forEach(Consumer { effect: PotionEffect? -> player.addPotionEffect(effect) }) }

        player.playSound(player.location, Sound.LEVEL_UP, 2f, 1f)
    }

    fun setRightClickEntityEvent(function: Consumer<PlayerInteractAtEntityEvent>) {
        rightClickEntityEventConsumer = function
    }

    fun onRightClickEntity(event: PlayerInteractAtEntityEvent) {
        Optional.ofNullable(rightClickEntityEventConsumer).ifPresent { function: Consumer<PlayerInteractAtEntityEvent>? -> function!!.accept(event) }
    }

    fun setLeftClickEvent(function: Consumer<PlayerInteractEvent>) {
        leftClickEventConsumer = function
    }

    fun onLeftClickEvent(event: PlayerInteractEvent) {
        Optional.ofNullable(leftClickEventConsumer).ifPresent { function: Consumer<PlayerInteractEvent>? -> function!!.accept(event) }
    }

    fun setDamagePlayerEvent(function: Consumer<EntityDamageByEntityEvent>) {
        damagePlayerEventConsumer = function
    }

    fun onRightClickEvent(event: PlayerInteractEvent) {
        Optional.ofNullable(rightClickEventConsumer).ifPresent { function: Consumer<PlayerInteractEvent>? -> function!!.accept(event) }
    }

    fun setInteractEvent(function: Consumer<PlayerInteractEvent>) {
        interactEventConsumer = function
    }

    fun onInteractEvent(event: PlayerInteractEvent) {
        Optional.ofNullable(interactEventConsumer).ifPresent { function: Consumer<PlayerInteractEvent>? -> function!!.accept(event) }
    }

    fun setDeathEvent(function: Consumer<PlayerDeathEvent>) {
        deathEventConsumer = function
    }

    fun onDeathEvent(event: PlayerDeathEvent) {
        Optional.ofNullable(deathEventConsumer).ifPresent { function: Consumer<PlayerDeathEvent>? -> function!!.accept(event) }
    }

    fun setMoveEvent(function: Consumer<PlayerMoveEvent>) {
        moveEventConsumer = function
    }

    fun onMoveEvent(event: PlayerMoveEvent) {
        Optional.ofNullable(moveEventConsumer).ifPresent { function: Consumer<PlayerMoveEvent>? -> function!!.accept(event) }
    }

    fun setRightClickEvent(function: Consumer<PlayerInteractEvent>) {
        rightClickEventConsumer = function
    }

    fun onDamagePlayer(event: EntityDamageByEntityEvent) {
        Optional.ofNullable(damagePlayerEventConsumer).ifPresent { function: Consumer<EntityDamageByEntityEvent>? -> function!!.accept(event) }
    }

    fun setPotionEffects(function: Function<Player, Collection<PotionEffect>>) {
        potionEffects = function
    }

    init {
        check(javaClass.isAnnotationPresent(KitInfo::class.java)) { javaClass.simpleName + " must annotate " + KitInfo::class.java.name }
        val annotation = javaClass.getAnnotation(KitInfo::class.java)
        name = annotation.name
        cost = annotation.cost
    }
}