package deob.condition;

import deob.TutorialScript;
import org.tbot.methods.Widgets;
import org.tbot.util.Condition;

public final class HasNoClosableWidgetCondition implements Condition {

    private final TutorialScript script;

    public HasNoClosableWidgetCondition(TutorialScript script) {
        this.script = script;
    }

    @Override
    public boolean check() {
        if (!Widgets.canClose()) {
            return true;
        }
        return false;
    }

}

