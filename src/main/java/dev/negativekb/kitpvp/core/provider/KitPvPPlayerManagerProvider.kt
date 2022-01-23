package dev.negativekb.kitpvp.core.provider

import dev.negativekb.api.util.Task
import dev.negativekb.api.util.cache.ObjectHashCache
import dev.negativekb.kitpvp.api.PlayerManager
import dev.negativekb.kitpvp.core.structure.Profile
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.IOException
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class KitPvPPlayerManagerProvider(plugin: JavaPlugin) : ObjectHashCache<UUID?, Profile?>(plugin.dataFolder.path + "/data/profiles.json"), PlayerManager {
    private var profiles = HashMap<UUID?, Profile?>()
    override fun createPlayer(uuid: UUID) {
        if (profiles.containsKey(uuid)) return

        val profile = Profile()
        profiles[uuid] = profile
    }

    override fun deleteProfile(uuid: UUID) {
        profiles.remove(uuid)
    }

    override fun getPlayer(uuid: UUID): CompletableFuture<Profile?> {
       return CompletableFuture.supplyAsync {
           profiles.getOrDefault(uuid, null)
       }
    }

    override fun updateProfile(uuid: UUID, function: Consumer<Profile>): CompletableFuture<Void> {
        return CompletableFuture.runAsync {
            // Will only accept the function if the profile is not null.
            val profile = profiles[uuid] ?: return@runAsync
            function.accept(profile)
        }
    }

    override fun updateProfile(player: Player, function: Consumer<Profile>): CompletableFuture<Void> {
        return updateProfile(player.uniqueId, function)
    }

    override fun updateProfile(player: OfflinePlayer, function: Consumer<Profile>): CompletableFuture<Void> {
        return updateProfile(player.uniqueId, function)
    }

    init {
        try {
            profiles = load()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Task.asyncRepeating(plugin, 0, (20 * 60).toLong()) {
            try {
                save(profiles)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}