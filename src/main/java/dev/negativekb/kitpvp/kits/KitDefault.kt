package dev.negativekb.kitpvp.kits

import dev.negativekb.kitpvp.core.structure.kit.KitInfo
import dev.negativekb.kitpvp.core.structure.kit.Kit
import org.bukkit.inventory.ItemStack
import dev.negativekb.api.util.ItemBuilder
import org.bukkit.Material
import java.util.HashMap
import org.bukkit.enchantments.Enchantment

@KitInfo(name = "Default")
class KitDefault : Kit() {

    override val helmet: ItemStack?
        get() = ItemBuilder(Material.IRON_HELMET).build()

    override val chestplate: ItemStack?
        get() = ItemBuilder(Material.IRON_CHESTPLATE).build()

    override val leggings: ItemStack?
        get() = ItemBuilder(Material.IRON_LEGGINGS).build()

    override val boots: ItemStack?
        get() = ItemBuilder(Material.IRON_BOOTS).build()

    override fun kitContents(): HashMap<Int, ItemStack> {
        val contents = HashMap<Int, ItemStack>()

        val sword = ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1)
                .addEnchant(Enchantment.DURABILITY, 5).build()

        contents[0] = sword
        return contents
    }

    override val icon: ItemStack?
        get() = null
}