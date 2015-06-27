package deob;

import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.actions.ObjectAction;
import org.tbot.wrappers.Tile;

public final class OpenLargeDoorObjectAction extends ObjectAction {

    public OpenLargeDoorObjectAction(Tile tile, String name, String action) {
        super(tile, name, action);
    }

    @Override
    public boolean traverse() {
        if (this.getGameObject() == null) {
            return Walking.walkTileMM(new Tile(3125, 3107));
        }

        return super.traverse();
    }
}

