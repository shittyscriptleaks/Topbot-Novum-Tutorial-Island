package deob.condition;

import org.tbot.methods.tabs.Inventory;
import org.tbot.util.Condition;
import org.tbot.wrappers.GameObject;

public final class UseTinOreOnObjectCondition implements Condition {

    private final GameObject object;

    public UseTinOreOnObjectCondition(GameObject gameObject) {
        this.object = gameObject;
    }

    @Override
    public boolean check() {
        return Inventory.useItemOn("Tin ore", this.object);
    }

}

