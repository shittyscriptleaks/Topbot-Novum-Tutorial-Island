package deob;

import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.actions.ObjectAction;
import org.tbot.wrappers.Tile;

public class OpenLargeDoorObjectAction extends ObjectAction {

    private final TutorialIslandArea area;

    public OpenLargeDoorObjectAction(TutorialIslandArea area, Tile tile, String name, String action) {
        super(tile, name, action);
        this.area = area;
    }

    @Override
    public boolean traverse() {
        if (this.getGameObject() == null) {
            return Walking.walkTileMM(new Tile(3125, 3107));
        }

        return super.traverse();
    }
}

