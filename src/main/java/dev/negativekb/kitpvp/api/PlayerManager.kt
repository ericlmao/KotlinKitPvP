package dev.negativekb.kitpvp.api

import dev.negativekb.kitpvp.core.structure.Profile
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface PlayerManager {
    fun createPlayer(uuid: UUID)

    fun deleteProfile(uuid: UUID)

    fun getPlayer(uuid: UUID) : CompletableFuture<Profile?>

    fun updateProfile(uuid: UUID, function: Consumer<Profile>) : CompletableFuture<Void>

    fun updateProfile(player: Player, function: Consumer<Profile>) : CompletableFuture<Void>

    fun updateProfile(player: OfflinePlayer, function: Consumer<Profile>) : CompletableFuture<Void>
}