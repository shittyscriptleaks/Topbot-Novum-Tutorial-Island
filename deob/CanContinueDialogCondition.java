package deob;

import org.tbot.methods.Widgets;
import org.tbot.util.Condition;

final class CanContinueDialogCondition implements Condition {

    @Override
    public boolean check() {
        return Widgets.canContinue();
    }

}

