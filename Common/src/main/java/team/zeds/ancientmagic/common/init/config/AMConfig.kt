package team.zeds.ancientmagic.common.init.config

import org.jetbrains.annotations.NotNull
import team.zeds.ancientmagic.common.AMConstant

class AMConfig {
    interface Common {
        fun isWayStoneCompact(): Boolean
        fun countOfDustConsuming(): Int
        fun isEasyMod(): Boolean
    }
    interface Client {
        fun isEnableShaders(): Boolean
    }

    companion object {
        @JvmStatic var common: Common? = null
            set(@NotNull value) {
                if (field != null) AMConstant.LOGGER.warn(
                    "Config was replaced! Old {} New {}",
                    field!!::class.java,
                    value!!::class.java
                )
                field = value
            }
        @JvmStatic var client: Client? = null
            set(@NotNull value) {
                if (field != null) AMConstant.LOGGER.warn(
                    "Config was replaced! Old {} New {}",
                    field!!::class.java,
                    value!!::class.java
                )
                field = value
            }
    }
}