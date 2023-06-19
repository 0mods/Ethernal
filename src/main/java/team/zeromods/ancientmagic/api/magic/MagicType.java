package team.zeromods.ancientmagic.api.magic;

import team.zeromods.ancientmagic.api.mod.Constant;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

@NotNull
public interface MagicType {
    String getId();

    int numerate();

    MutableComponent getTranslation();

    MagicClassifier getClassifier();

    ChatFormatting[] getStyles();

    static MutableComponent getMagicMessage(String message, Object... objects) {
        return Component.translatable(String.format("magic.%s.%s", Constant.Key, message), objects);
    }

    static MutableComponent getMagicTypeMessage(String message, Object... objects) {
        return Component.translatable(String.format("magicType.%s", message), objects);
    }

    enum MagicClassifier {
        MAIN_TYPE, SUBTYPE
    }
}