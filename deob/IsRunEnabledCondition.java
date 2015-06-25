package deob;

import org.tbot.methods.walking.Walking;
import org.tbot.util.Condition;

public final class IsRunEnabledCondition implements Condition {

    @Override
    public boolean check() {
        return Walking.isRunEnabled();
    }

}

