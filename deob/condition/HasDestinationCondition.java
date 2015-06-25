package deob.condition;

import org.tbot.methods.walking.Walking;
import org.tbot.util.Condition;

public final class HasDestinationCondition implements Condition {

    @Override
    public boolean check() {
        if (Walking.getDestination() != null) {
            return true;
        }
        return false;
    }

}

