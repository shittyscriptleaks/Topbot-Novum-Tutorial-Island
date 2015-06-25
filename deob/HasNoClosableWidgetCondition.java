package deob;

import org.tbot.methods.Widgets;
import org.tbot.util.Condition;

class HasNoClosableWidgetCondition implements Condition {

    final TutorialScript script;

    @Override
    public boolean check() {
        if (!Widgets.canClose()) {
            return true;
        }
        return false;
    }

    HasNoClosableWidgetCondition(TutorialScript script) {
        this.script = script;
    }

}

