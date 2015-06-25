package deob;

import org.tbot.methods.Widgets;
import org.tbot.util.Condition;

final class MakeoverWidgetNotOpenCondition implements Condition {

    @Override
    public boolean check() {
        if (!Widgets.getWidget(269, 113).isVisible()) {
            return true;
        }

        return false;
    }

}

