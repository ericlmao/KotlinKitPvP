package dev.negativekb.kitpvp

import dev.negativekb.api.BasePlugin
import dev.negativekb.kitpvp.commands.CommandKitPvP
import dev.negativekb.kitpvp.core.provider.KitPvPAPIProvider
import dev.negativekb.kitpvp.listeners.PlayerJoinListener

class KotlinKitPvP : BasePlugin() {
    override fun onEnable() {
        super.onEnable()
        instance = this
        KitPvPAPIProvider(this)

        registerCommands(
                CommandKitPvP()
        )

        registerListeners(
                PlayerJoinListener()
        )

    }

    companion object {
        @JvmStatic
        var instance: KotlinKitPvP? = null
            private set
    }
}