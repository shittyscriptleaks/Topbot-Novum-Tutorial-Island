package deob;

import org.tbot.methods.Bank;
import org.tbot.util.Condition;

class IsBankNotOpenCondition implements Condition {

    final TutorialScript a;

    @Override
    public boolean check() {
        if (!Bank.isOpen()) {
            return true;
        }
        return false;
    }

    IsBankNotOpenCondition(TutorialScript cc) {
        this.a = cc;
    }
}

