package deob.condition;

import org.tbot.methods.Widgets;
import org.tbot.util.Condition;

public final class CanContinueDialogCondition implements Condition {

    @Override
    public boolean check() {
        return Widgets.canContinue();
    }

}

