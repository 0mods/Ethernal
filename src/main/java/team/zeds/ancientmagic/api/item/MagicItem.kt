@file:Suppress("unused", "SENSELESS_COMPARISON")

package team.zeds.ancientmagic.api.item

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import org.jetbrains.annotations.Nullable
import team.zeds.ancientmagic.api.atomic.KAtomicUse
import team.zeds.ancientmagic.api.cap.ItemStackMagic
import team.zeds.ancientmagic.api.magic.MagicType
import team.zeds.ancientmagic.api.magic.MagicType.MagicClassifier
import team.zeds.ancientmagic.api.magic.MagicTypes
import team.zeds.ancientmagic.init.registries.AMCapability
import kotlin.math.max
import kotlin.math.min

interface IMagicItem {
    fun use(use: KAtomicUse<ItemStack>)
    fun useOn(use: KAtomicUse<*>)
}

open class MagicItem(private val builder: MagicItemBuilder): Item(builder.getProperties()), IMagicItem, ItemStackMagic {
    private var canUseItem = true
    private var itemStack: ItemStack? = null

    init {
        this.builder.getProperties().setNoRepair()
        this.setMagicType(this.builder.getMagicType())
        this.setMaxMana(this.builder.getMaxMana())
    }

    @Deprecated("Use \"use\" in MagicItem")
    override fun use(
        level: Level,
        player: Player,
        hand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val atomicUse: KAtomicUse<ItemStack> = KAtomicUse(player, level, hand)
        val stack = atomicUse.stack
        if (!stack.`is`(this)) return InteractionResultHolder.fail(stack)
        if (getItemUse()) {
            if (getBuilder().getManaCount() != 0 && getBuilder().getManaCount() != 0) {
                subMana(this.getBuilder().getSubMana())
                this.use(atomicUse)
                return atomicUse.returnHolder!!
            } else if (getBuilder().getMaxMana() != 0 && getBuilder().getManaCount() == 0) {
                player.displayClientMessage(MagicType.getMagicMessage("notMana", getName(stack)), true)
                return InteractionResultHolder.fail(stack)
            } else if (getBuilder().getMaxMana() == 0) {
                this.use(atomicUse)
                return atomicUse.returnHolder!!
            }
        }
        return InteractionResultHolder.pass(stack)
    }

    @Deprecated("Use \"useOn\" in MagicItem")
    override fun useOn(use: UseOnContext): InteractionResult {
        val context: KAtomicUse<*> = KAtomicUse<Any>(use)
        val stack = context.stack
        val player = context.player
        if (getItemUse()) {
            if (getBuilder().getManaCount() != 0 && getBuilder().getManaCount() != 0) {
                subMana(this.getBuilder().getSubMana())
                this.useOn(context)
                return context.returnResult!!
            } else if (getBuilder().getMaxMana() != 0 && getBuilder().getManaCount() == 0) {
                player.displayClientMessage(MagicType.getMagicMessage("notMana", getName(stack)), true)
                return InteractionResult.FAIL
            } else if (getBuilder().getMaxMana() == 0) {
                this.useOn(context)
                return context.returnResult!!
            }
        }
        return InteractionResult.PASS
    }

    override fun use(use: KAtomicUse<ItemStack>) {}

    override fun useOn(use: KAtomicUse<*>) {}

    fun getBuilder(): MagicItemBuilder {
        return builder
    }

    fun getItemUse(): Boolean {
        return canUseItem
    }

    fun setItemUse(canUse: Boolean) {
        canUseItem = canUse
    }

    override fun getMagicType(): MagicType {
        return this.getBuilder().getMagicType()
    }

    @Nullable
    override fun getMagicSubtype(): MagicType {
        return this.getBuilder().getMagicSubtype()
    }

    override fun setMaxMana(max: Int) {
        this.getStack().orCreateTag.putInt("MaxMana", max)
    }

    override fun setMagicType(type: MagicType) {
        this.getStack().orCreateTag.putInt("MagicLevel", type.numerate())
    }

    override fun getMaxMana(): Int {
        return this.getStack().orCreateTag.getInt("MaxMana")
    }

    override fun getStorageMana(): Int {
        val tag = this.getStack().orCreateTag

        return if (tag.get("ManaStorage") == null) {
            return if (getMaxMana() != 0) {
                tag.putInt("ManaStorage", 0)
                tag.getInt("ManaStorage")
            } else 0
        } else tag.getInt("ManaStorage")
    }

    override fun addMana(count: Int) {
        val storage = this.getStorageMana()
        val calc = min(storage + count, getMaxMana())

        this.getStack().orCreateTag.putInt("ManaStorage", calc)
    }

    override fun subMana(count: Int) {
        val storage = this.getStorageMana()
        max(storage - count, 0)
    }

    override fun getStack(): ItemStack {
        return this.itemStack!!
    }

    override fun setStack(stack: ItemStack) {
        this.itemStack = stack
    }
}

@Suppress("unused")
open class MagicItemBuilder private constructor() {
    private var properties = Item.Properties()

    private var magicType: MagicType = MagicTypes.LOW_MAGIC

    private var magicSubtype: MagicType? = null
    private var maxMana = 0
    private var manaCount = 0
    private var subManaIfUse = 0

    fun setMaxMana(maxMana: Int): MagicItemBuilder {
        this.maxMana = maxMana
        return this
    }

    fun setManaCount(count: Int): MagicItemBuilder {
        manaCount = count
        return this
    }

    fun setSubManaIfUse(subManaIfUse: Int): MagicItemBuilder {
        this.subManaIfUse = subManaIfUse
        return this
    }

    fun fireProof(): MagicItemBuilder {
        properties.fireResistant()
        return this
    }

    fun setFood(properties: FoodProperties): MagicItemBuilder {
        this.properties.food(properties)
        return this
    }

    fun setMagicType(type: MagicType): MagicItemBuilder {
        if (type.getClassifier() == MagicClassifier.MAIN_TYPE) {
            magicType = type
            return this
        } else throw RuntimeException("Classifier ${type.getClassifier().declaringJavaClass} isn't ${MagicClassifier.MAIN_TYPE}. Accepts only ${MagicClassifier.MAIN_TYPE}!")
    }

    fun setMagicSubtype(type: MagicType): MagicItemBuilder {
        if (type.getClassifier() == MagicClassifier.SUBTYPE && this.getMagicType() != null) {
            magicSubtype = type
            return this
        } else throw RuntimeException("Classifier ${type.getClassifier().declaringJavaClass} isn't ${MagicClassifier.SUBTYPE}. Accepts only ${MagicClassifier.SUBTYPE}!")
    }

    fun setRarity(rarity: Rarity): MagicItemBuilder {
        properties.rarity(rarity)
        return this
    }

    fun setRemainder(itemToRemained: Item): MagicItemBuilder {
        properties.craftRemainder(itemToRemained)
        return this
    }

    fun setStacks(stacksTo: Int): MagicItemBuilder {
        properties.stacksTo(stacksTo)
        return this
    }

    fun getManaCount() : Int = manaCount

    fun getMaxMana() : Int = maxMana

    fun getSubMana() : Int = subManaIfUse

    fun getMagicType() : MagicType = magicType

    @Nullable
    fun getMagicSubtype() : MagicType = magicSubtype!!

    fun getProperties(): Item.Properties = properties

    companion object {
        @JvmStatic
        fun get(): MagicItemBuilder {
            return MagicItemBuilder()
        }
    }
}