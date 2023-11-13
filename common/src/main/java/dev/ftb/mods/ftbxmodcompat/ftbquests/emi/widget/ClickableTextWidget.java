package dev.ftb.mods.ftbxmodcompat.ftbquests.emi.widget;

import dev.emi.emi.api.widget.TextWidget;
import net.minecraft.util.FormattedCharSequence;

import java.util.function.Consumer;

public class ClickableTextWidget extends TextWidget {
    private final Consumer<ClickableTextWidget> onClick;

    public ClickableTextWidget(FormattedCharSequence text, int x, int y, int color, boolean shadow, Consumer<ClickableTextWidget> onClick) {
        super(text, x, y, color, shadow);
        this.onClick = onClick;
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (getBounds().contains(mouseX, mouseY)) {
            onClick.accept(this);
            return true;
        }
        return false;
    }
}
