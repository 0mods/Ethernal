package team._0mods.aeternus.api.event.base.client

import net.minecraft.client.player.LocalPlayer
import team._0mods.aeternus.api.event.core.EventFactory

interface ClientPlayerEvent {
    companion object {
        @JvmField val PLAYER_JOIN = EventFactory.createNoResult<PlayerJoin>()
        @JvmField val PLAYER_LEAVE = EventFactory.createNoResult<PlayerLeave>()
        @JvmField val PLAYER_CLONE = EventFactory.createNoResult<PlayerClone>()
    }

    fun interface PlayerJoin {
        fun onJoin(player: LocalPlayer)
    }

    fun interface PlayerLeave {
        fun onLeave(player: LocalPlayer?)
    }

    fun interface PlayerClone {
        fun onClone(old: LocalPlayer, new: LocalPlayer)
    }
}