package deob;

import org.tbot.methods.tabs.Inventory;
import org.tbot.util.Condition;
import org.tbot.wrappers.GameObject;

final class UseTinOreOnObjectCondition implements Condition {

    final GameObject object;

    public boolean check() {
        return Inventory.useItemOn("Tin ore", this.object);
    }

    UseTinOreOnObjectCondition(GameObject gameObject) {
        this.object = gameObject;
    }

}

