package deob.condition;

import org.tbot.methods.Widgets;
import org.tbot.util.Condition;

public final class MakeoverWidgetNotOpenCondition implements Condition {

    @Override
    public boolean check() {
        if (!Widgets.getWidget(269, 113).isVisible()) {
            return true;
        }

        return false;
    }

}

