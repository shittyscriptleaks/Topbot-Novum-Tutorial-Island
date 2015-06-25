package deob;

import org.tbot.util.Condition;
import org.tbot.wrappers.WidgetChild;

class IsWidgetNotOpenCondition implements Condition {

    final TutorialScript script;
    final WidgetChild widget;

    @Override
    public boolean check() {
        if (!this.widget.isVisible()) {
            return true;
        }
        return false;
    }

    IsWidgetNotOpenCondition(TutorialScript script, WidgetChild widgetChild) {
        this.script = script;
        this.widget = widgetChild;
    }

}

