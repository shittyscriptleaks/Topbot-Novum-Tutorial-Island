package deob.condition;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsNotCastingWindStrikeCondition implements Condition {

    @Override
    public boolean check() {
        if (Settings.get(281) != 650) {
            return true;
        }
        return false;
    }

}

