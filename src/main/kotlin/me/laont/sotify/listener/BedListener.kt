package me.laont.sotify.listener

import me.laont.sotify.Sotify
import me.laont.sotify.util.Message
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerBedLeaveEvent

class BedListener(private val sotify: Sotify) : Listener {
    @EventHandler
    fun onBedEnter(event: PlayerBedEnterEvent) {
        if (sotify.config.getBoolean("enabled")) {
            if (event.bedEnterResult == PlayerBedEnterEvent.BedEnterResult.OK) {
                Message(event, sotify).broadcast()
            }
        }
    }

    @EventHandler
    fun onBedLeave(event: PlayerBedLeaveEvent) {
        if (sotify.config.getBoolean("enabled")) {
            if (sotify.config.getBoolean("debug")) {
                sotify.server.broadcast(
                    sotify.mm.deserialize(
                        "${event.player.name}: ${event.player.world.time.toString()}"
                    )
                )
            }
            if (event.player.world.time != 0L) {
                Message(event, sotify).broadcast()
            }
        }
    }
}