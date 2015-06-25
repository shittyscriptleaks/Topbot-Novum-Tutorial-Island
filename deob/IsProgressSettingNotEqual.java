package deob;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

final class IsProgressSettingNotEqual implements Condition {

    final int to;

    IsProgressSettingNotEqual(int n) {
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

