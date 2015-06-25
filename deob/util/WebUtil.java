package deob.util;

import deob.TutorialIslandArea;
import org.tbot.bot.TBot;

public final class WebUtil {

    private WebUtil() { }

    public static void correctWeb() {
        TBot.getBot().getDefaultWeb().getData().add(new TutorialIslandArea());
    }

}

