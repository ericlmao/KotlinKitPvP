package dev.negativekb.kitpvp.commands

import dev.negativekb.api.commands.Command
import dev.negativekb.api.commands.annotation.CommandInfo
import org.bukkit.command.CommandSender

@CommandInfo(name = "kit", aliases = ["kits"], playerOnly = true)
class CommandKit : Command() {
    override fun onCommand(sender: CommandSender?, args: Array<out String>?) {
        TODO("Not yet implemented")
    }
}