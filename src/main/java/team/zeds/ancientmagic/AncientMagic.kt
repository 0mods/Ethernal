package team.zeds.ancientmagic

import net.minecraftforge.fml.common.Mod
import team.zeds.ancientmagic.api.mod.AMConstant
import team.zeds.ancientmagic.init.AMManage

@Mod(AMConstant.KEY)
class AncientMagic {
    init {
        AMConstant.LOGGER.debug("Setup a Mod!")
        AMManage.init()
    }
}
