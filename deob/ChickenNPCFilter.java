package deob;

import org.tbot.util.Filter;
import org.tbot.wrappers.NPC;

public final class ChickenNPCFilter implements Filter<NPC> {

    @Override
    public boolean accept(NPC npc) {
        if (npc.getInteractingEntity() == null && npc.getName().equals("Chicken")) {
            return true;
        }

        return false;
    }

}

