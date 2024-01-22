package team._0mods.aeternus.mixin.goal

import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.animal.Panda
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import team._0mods.aeternus.init.registry.AeternusRegsitry

@Mixin(targets = ["net.minecraft.world.entity.animal.Panda\$PandaAttackGoal"])
abstract class PandaAttackGoalMixin(mob: PathfinderMob, speedMod: Double, followIfNotSeen: Boolean) : MeleeAttackGoal(mob, speedMod,
    followIfNotSeen
) {
    @Inject(method = ["canUse"], at = [At("RETURN")], cancellable = true)
    fun canUseInj(cir: CallbackInfoReturnable<Boolean>) {
        if (mob.level().isNight && mob.level().dimensionTypeId() == AeternusRegsitry.alTakeDim)
            cir.returnValue = true
        else cir.returnValue = (this.mob as Panda).canPerformAction() && super.canUse()
    }
}