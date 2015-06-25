package deob.condition;

import deob.TutorialScript;
import org.tbot.util.Condition;
import org.tbot.wrappers.WidgetChild;

public final class IsWidgetNotOpenCondition implements Condition {

    private final TutorialScript script;
    private final WidgetChild widget;

    public IsWidgetNotOpenCondition(TutorialScript script, WidgetChild widgetChild) {
        this.script = script;
        this.widget = widgetChild;
    }

    @Override
    public boolean check() {
        if (!this.widget.isVisible()) {
            return true;
        }
        return false;
    }

}

