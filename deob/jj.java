package deob;

import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.actions.ObjectAction;
import org.tbot.wrappers.Tile;

class jj extends ObjectAction {

    final TutorialIslandArea area;

    jj(TutorialIslandArea TutorialIslandArea, Tile tile, String string, String string2) {
        super(tile, string, string2);
        this.area = TutorialIslandArea;
    }

    @Override
    public boolean traverse() {
        if (this.getGameObject() == null) {
            return Walking.walkTileMM(new Tile(3125, 3107));
        }

        return super.traverse();
    }
}

