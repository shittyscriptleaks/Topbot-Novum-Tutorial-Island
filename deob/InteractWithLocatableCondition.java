package deob;

import org.tbot.util.Condition;
import org.tbot.wrappers.Interactable;
import org.tbot.wrappers.Locatable;

final class InteractWithLocatableCondition implements Condition {
    final Locatable locatable;
    final String action;

    InteractWithLocatableCondition(Locatable locatable, String action) {
        this.locatable = locatable;
        this.action = action;
    }

    @Override
    public boolean check() {
        return ((Interactable)this.locatable).interact(this.action);
    }
}

