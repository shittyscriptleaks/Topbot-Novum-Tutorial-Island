package deob;

import org.tbot.methods.Bank;
import org.tbot.util.Condition;

public final class IsBankNotOpenCondition implements Condition {

    private final TutorialScript a;

    public IsBankNotOpenCondition(TutorialScript cc) {
        this.a = cc;
    }

    @Override
    public boolean check() {
        if (!Bank.isOpen()) {
            return true;
        }
        return false;
    }

}

