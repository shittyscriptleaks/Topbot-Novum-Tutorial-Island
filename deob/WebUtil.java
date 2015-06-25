package deob;

import org.tbot.bot.TBot;

public final class WebUtil {

    private WebUtil() { }

    public static String decryptStringStub(String string) {
        int n = string.length();
        int n2 = n - 1;
        char[] arrc = new char[n];
        int n3 = 1 << 3 ^ (3 ^ 5);
        int n4 = n2;
        int n5 = 5 << 4 ^ 3;
        while (n4 >= 0) {
            int n6 = n2--;
            arrc[n6] = (char)(string.charAt(n6) ^ n5);
            if (n2 < 0) break;
            int n7 = n2--;
            arrc[n7] = (char)(string.charAt(n7) ^ n3);
            n4 = n2;
        }
        return new String(arrc);
    }

    public static void correctWeb() {
        TBot.getBot().getDefaultWeb().getData().add(new TutorialIslandArea());
    }

}

