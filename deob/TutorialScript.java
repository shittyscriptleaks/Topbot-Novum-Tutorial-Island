package deob;

import deob.step.*;
import deob.util.WebUtil;
import org.tbot.bot.TBot;
import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.event.listeners.PaintListener;
import org.tbot.internal.handlers.RandomHandler;
import org.tbot.methods.*;
import org.tbot.methods.input.keyboard.Keyboard;
import org.tbot.wrappers.WidgetChild;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Manifest(name = "Tutorial Island",
        description = "Completes tutorial island for you.. for free!",
        authors = { "Novum" },
        openSource = true,
        category = ScriptCategory.OTHER)
public final class TutorialScript extends AbstractScript implements PaintListener {

    private final List<TutorialStep> steps = new LinkedList<>();

    public TutorialScript() {
        TBot.getBot().getScriptHandler().getEventManager().addListener(this);
    }

    @Override
    public boolean onStart() {
        TBot.getBot().getScriptHandler().getRandomHandler().disableAll();
        TBot.getBot().getScriptHandler().getRandomHandler().get(RandomHandler.AUTO_LOGIN).enable();
        TBot.getBot().getScriptHandler().getRandomHandler().get(RandomHandler.TOGGLE_ROOF).enable();
        WebUtil.correctWeb();

        steps.add(new BankAndPollStep());
        steps.add(new BrotherBraceStep());
        steps.add(new CombatInstructorStep());
        steps.add(new CookingGuyStep());
        steps.add(new FinancialAdvisorStep());
        steps.add(new MagicInstructorStep());
        steps.add(new QuestGuyStep());
        steps.add(new RunescapeGuideStep());
        steps.add(new SmithingGuyStep());
        steps.add(new SurvivalExpertStep());
        return true;
    }

    @Override
    public int loop() {
        if (!this.globalCheck()) {
            if (Camera.getPitch() < 70) {
                Camera.setPitch(Random.nextInt(75, 85));
                return 600;
            }

            steps.stream().filter(s -> !s.hasProgressedPast()).forEach(TutorialStep::handle);

            if (Settings.get(Constants.PROGRESS_SETTING_ID) >= 1000 && Game.logout()) {
                TBot.getBot().getScriptHandler().stopScript();
            }
        }

        return Random.nextInt(400, 600);
    }

    private boolean globalCheck() {
        if (Widgets.canContinue()) {
            Keyboard.sendKey(' ');
            Time.sleep(250, 700);
            return true;
        }

        WidgetChild widgetChild = Widgets.getWidget(162, 33);
        if (widgetChild != null && widgetChild.isVisible()) {
            widgetChild.click();
            return true;
        }

        widgetChild = Widgets.getWidget(548, 120);
        if (widgetChild != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleep(500, 1500);
            return true;
        }

        widgetChild = Widgets.getWidget(219, 0);
        if (widgetChild != null && widgetChild.isVisible() && (widgetChild = widgetChild.getChild(1)) != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleep(500, 1500);
            return true;
        }

        WidgetChild widgetChild2 = Widgets.getWidget(84, 4);
        if (widgetChild2 != null && widgetChild2.isVisible() && widgetChild2.click()) {
            Time.sleepUntil(() -> !widgetChild2.isVisible(), 1000);
            return true;
        }

        widgetChild = Widgets.getWidget(310, 1);
        if (widgetChild != null && widgetChild.isVisible() && (widgetChild = widgetChild.getChild(11)) != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleep(500, 1500);
            return true;
        }

        if (Bank.isOpen() && Bank.close()) {
            Time.sleepUntil(() -> !Bank.isOpen(), 2000);
            return true;
        }

        if (Widgets.canClose() && Widgets.close()) {
            Time.sleepUntil(() -> !Widgets.canClose(), 2000);
            return true;
        }

        return false;
    }

    @Override
    public void onRepaint(Graphics graphics) {
        graphics.drawString("" + Settings.get(Constants.PROGRESS_SETTING_ID), 20, 120);
    }

}

