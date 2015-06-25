package deob;

import org.tbot.util.Filter;
import org.tbot.wrappers.NPC;

final class RatNPCFilter implements Filter<NPC> {

    @Override
    public boolean accept(NPC npc) {
        if (npc.getName() != null && npc.getInteractingEntity() == null && npc.getName().equals("Rat")) {
            return true;
        }
        return false;
    }

}

