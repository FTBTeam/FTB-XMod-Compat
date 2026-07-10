package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.patchouli;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.jetbrains.annotations.Nullable;
import vazkii.patchouli.api.PatchouliAPI;

public class PatchouliClient {
    private static Screen prevGui;
    private static Screen patchouliGui;

    static void openBook(ResourceLocation bookId, @Nullable ResourceLocation pageId, String anchor) {
        try {
            // patchouli does not track the previous screen, so we need to handle that ourselves
            Screen prev = Minecraft.getInstance().screen;
            int pageNum = anchor.isEmpty() ? 1 : Integer.parseInt(anchor);
            PatchouliAPI.get().openBookEntry(bookId, pageId, pageNum);
            // patchouli screen *should* be open now, but let's be careful...
            if (Minecraft.getInstance().screen != null && Minecraft.getInstance().screen.getClass().getPackageName().startsWith("vazkii.patchouli")) {
                patchouliGui = Minecraft.getInstance().screen;
                prevGui = prev;
            } else {
                patchouliGui = null;
                prevGui = null;
            }
        } catch (NumberFormatException ignored) {
        }
    }

    @EventBusSubscriber(modid = FTBXModCompat.MOD_ID, value = Dist.CLIENT)
    private static class ScreenListener {
        @SubscribeEvent
        public static void clientTick(ClientTickEvent.Post event) {
            if (Minecraft.getInstance().screen == null && prevGui != null && patchouliGui != null) {
                // this means Patchouli was opened from our screen, and then closed
                // - so reopen our screen
                Minecraft.getInstance().setScreen(prevGui);
                prevGui = null;
                patchouliGui = null;
            }
        }
    }
}
