package deob.condition;

import org.tbot.util.Condition;
import org.tbot.wrappers.Interactable;
import org.tbot.wrappers.Locatable;

public final class InteractWithLocatableCondition implements Condition {

    private final Locatable locatable;
    private final String action;

    public InteractWithLocatableCondition(Locatable locatable, String action) {
        this.locatable = locatable;
        this.action = action;
    }

    @Override
    public boolean check() {
        return ((Interactable)this.locatable).interact(this.action);
    }
}

