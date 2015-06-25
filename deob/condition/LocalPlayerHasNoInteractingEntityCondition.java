package deob.condition;

import org.tbot.methods.Players;
import org.tbot.util.Condition;
import org.tbot.wrappers.Character;

public final class LocalPlayerHasNoInteractingEntityCondition implements Condition {

    @Override
    public boolean check() {
        if (Players.getLocal().getInteractingEntity() == null) {
            return true;
        }

        return false;
    }
}
