package dev.negativekb.kitpvp.commands

import dev.negativekb.api.commands.Command
import dev.negativekb.kitpvp.KotlinKitPvP.Companion.instance
import dev.negativekb.kitpvp.KotlinKitPvP
import org.bukkit.command.CommandSender

class CommandKitPvP : Command() {
    private val plugin: KotlinKitPvP? = instance

    override fun onCommand(sender: CommandSender, args: Array<String>) {

    }

}