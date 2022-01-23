package dev.negativekb.kitpvp.api

import dev.negativekb.kitpvp.api.PlayerManager
import dev.negativekb.kitpvp.api.KitPvPAPI

abstract class KitPvPAPI {
    abstract val playerManager: PlayerManager?

    companion object {
        var instance: KitPvPAPI? = null
    }
}