package deob;

import org.tbot.util.Filter;
import org.tbot.wrappers.NPC;

public final class RatNPCFilter implements Filter<NPC> {

    @Override
    public boolean accept(NPC npc) {
        if (npc.getName() != null && npc.getInteractingEntity() == null && npc.getName().equals("Giant rat")) {
            return true;
        }
        return false;
    }

}

