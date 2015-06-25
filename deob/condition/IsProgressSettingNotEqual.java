package deob.condition;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsProgressSettingNotEqual implements Condition {

    private final int to;

    public IsProgressSettingNotEqual(int n) {
        this.to = n;
    }

    @Override
    public boolean check() {
        if (Settings.get(281) != this.to) {
            return true;
        }

        return false;
    }

}

