package team._0mods.aeternus.forge.service

import team._0mods.aeternus.api.event.core.EventFactory
import team._0mods.aeternus.api.event.core.EventHandler
import team._0mods.aeternus.api.gui.event.ScreenAccess
import team._0mods.aeternus.forge.event.EventFactoryImpl
import team._0mods.aeternus.service.core.IEventHelper

class ForgeEventHelper: IEventHelper {
    override val screenAccess: ScreenAccess
        get() = TODO("Not yet implemented")
    override val eventHandler: EventHandler
        get() = TODO("Not yet implemented")
    override val eventFactory: EventFactory
        get() = EventFactoryImpl
}