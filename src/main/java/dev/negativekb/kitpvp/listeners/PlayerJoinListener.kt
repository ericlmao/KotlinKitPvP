package dev.negativekb.kitpvp.listeners

import dev.negativekb.kitpvp.api.KitPvPAPI
import dev.negativekb.kitpvp.api.PlayerManager
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerJoinListener : Listener {
    private val playerManager: PlayerManager = KitPvPAPI.instance!!.playerManager!!

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        playerManager.createPlayer(player.uniqueId)
    }

}