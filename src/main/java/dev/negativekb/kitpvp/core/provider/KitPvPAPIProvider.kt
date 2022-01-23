package dev.negativekb.kitpvp.core.provider

import dev.negativekb.kitpvp.api.KitPvPAPI
import dev.negativekb.kitpvp.api.KitPvPAPI.Companion.instance
import dev.negativekb.kitpvp.api.PlayerManager
import org.bukkit.plugin.java.JavaPlugin

class KitPvPAPIProvider(plugin: JavaPlugin) : KitPvPAPI() {

    override val playerManager: PlayerManager

    init {
        instance = this
        playerManager = KitPvPPlayerManagerProvider(plugin)
    }
}