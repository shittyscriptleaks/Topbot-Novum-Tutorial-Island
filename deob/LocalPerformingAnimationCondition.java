package deob;

import org.tbot.methods.Players;
import org.tbot.util.Condition;

final class LocalPerformingAnimationCondition implements Condition {

    @Override
    public boolean check() {
        if (Players.getLocal().getAnimation() != -1) {
            return true;
        }
        return false;
    }

}

