package deob;

import org.tbot.methods.web.Web;
import org.tbot.methods.web.actions.ObjectAction;
import org.tbot.methods.web.areas.WebArea;
import org.tbot.methods.web.nodes.WebNode;
import org.tbot.methods.web.nodes.connections.ActionConnection;
import org.tbot.wrappers.Tile;

final class TutorialIslandArea extends WebArea {

    @Override
    public void addTo(Web web) {
        web.getData().enableTeleports(false);
        web.getData().enableGlories(false);
        web.getData().enableTeleportTabs(false);
        web.getData().enableGamesNecklaces(false);
        WebNode webNode = web.getNode(new Tile(3097, 3107));
        WebNode webNode2 = web.getNode(new Tile(3098, 3107));
        WebNode webNode3 = web.getNode(new Tile(3090, 3092));
        WebNode webNode4 = web.getNode(new Tile(3089, 3092));
        WebNode webNode5 = web.getNode(new Tile(3079, 3084));
        WebNode webNode6 = web.getNode(new Tile(3078, 3084));
        WebNode webNode7 = web.getNode(new Tile(3073, 3090));
        WebNode webNode8 = web.getNode(new Tile(3072, 3090));
        WebNode webNode9 = web.getNode(new Tile(3071, 3106));
        WebNode webNode10 = web.getNode(new Tile(3073, 3123));
        WebNode webNode11 = web.getNode(new Tile(3086, 3126));
        WebNode webNode12 = web.getNode(new Tile(3086, 3125));
        WebNode webNode13 = web.getNode(new Tile(3088, 3120));
        WebNode webNode14 = web.getNode(new Tile(3088, 9520));
        WebNode webNode15 = web.getNode(new Tile(3082, 9508));
        WebNode webNode16 = web.getNode(new Tile(3094, 9503));
        WebNode webNode17 = web.getNode(new Tile(3095, 9503));
        WebNode webNode18 = web.getNode(new Tile(3107, 9509));
        WebNode webNode19 = web.getNode(new Tile(3111, 9518));
        WebNode webNode20 = web.getNode(new Tile(3110, 9518));
        WebNode webNode21 = web.getNode(new Tile(3111, 9525));
        WebNode webNode22 = web.getNode(new Tile(3112, 3126));
        WebNode webNode23 = web.getNode(new Tile(3124, 3124));
        WebNode webNode24 = web.getNode(new Tile(3125, 3124));
        WebNode webNode25 = web.getNode(new Tile(3129, 3124));
        WebNode webNode26 = web.getNode(new Tile(3130, 3124));
        WebNode webNode27 = web.getNode(new Tile(3129, 3107));
        WebNode webNode28 = web.getNode(new Tile(3128, 3107));
        WebNode webNode29 = web.getNode(new Tile(3122, 3103));
        WebNode webNode30 = web.getNode(new Tile(3122, 3102));
        WebNode webNode31 = web.getNode(new Tile(3130, 3093));
        WebNode webNode32 = web.getNode(new Tile(3141, 3087));
        web.addWalkConnection(webNode2, webNode3);
        web.addWalkConnection(webNode4, webNode5);
        web.addWalkConnection(webNode6, webNode7);
        web.addWalkConnection(webNode8, webNode9);
        web.addWalkConnection(webNode9, webNode10);
        web.addWalkConnection(webNode10, webNode11);
        web.addWalkConnection(webNode12, webNode13);
        web.addWalkConnection(webNode14, webNode15);
        web.addWalkConnection(webNode15, webNode16);
        web.addWalkConnection(webNode17, webNode18);
        web.addWalkConnection(webNode18, webNode19);
        web.addWalkConnection(webNode18, webNode21);
        web.addWalkConnection(webNode22, webNode23);
        web.addWalkConnection(webNode24, webNode25);
        web.addWalkConnection(webNode26, webNode27);
        web.addWalkConnection(webNode28, webNode29);
        web.addWalkConnection(webNode30, webNode31);
        web.addWalkConnection(webNode31, webNode32);
        webNode29.addConnection(new ActionConnection(webNode29, webNode30, new ObjectAction(webNode30.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode27.addConnection(new ActionConnection(webNode27, webNode28, new jj(this, webNode27.getLocation(), WebUtil.decryptStringStub("B2|4ksj<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode25.addConnection(new ActionConnection(webNode25, webNode26, new ObjectAction(webNode26.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode23.addConnection(new ActionConnection(webNode23, webNode24, new ObjectAction(webNode24.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode21.addConnection(new ActionConnection(webNode21, webNode22, new ObjectAction(new Tile(3111, 9526), WebUtil.decryptStringStub("B2j7k!"), WebUtil.decryptStringStub("M?g>l~{#"))));
        webNode20.addConnection(new ActionConnection(webNode20, webNode19, new ObjectAction(webNode19.getLocation(), WebUtil.decryptStringStub("I2z6"), WebUtil.decryptStringStub("A#k="))));
        webNode19.addConnection(new ActionConnection(webNode19, webNode20, new ObjectAction(webNode19.getLocation(), WebUtil.decryptStringStub("I2z6"), WebUtil.decryptStringStub("A#k="))));
        webNode16.addConnection(new ActionConnection(webNode16, webNode17, new ObjectAction(webNode16.getLocation(), WebUtil.decryptStringStub("I2z6"), WebUtil.decryptStringStub("A#k="))));
        webNode13.addConnection(new ActionConnection(webNode13, webNode14, new ObjectAction(new Tile(3088, 3119), WebUtil.decryptStringStub("B2j7k!"), WebUtil.decryptStringStub("M?g>l~j<y="))));
        webNode11.addConnection(new ActionConnection(webNode11, webNode12, new ObjectAction(webNode11.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode7.addConnection(new ActionConnection(webNode7, webNode8, new ObjectAction(webNode8.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode5.addConnection(new ActionConnection(webNode5, webNode6, new ObjectAction(webNode5.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
        webNode3.addConnection(new ActionConnection(webNode3, webNode4, new ObjectAction(webNode4.getLocation(), WebUtil.decryptStringStub("I2z6"), WebUtil.decryptStringStub("A#k="))));
        webNode.addConnection(new ActionConnection(webNode, webNode2, new ObjectAction(webNode2.getLocation(), WebUtil.decryptStringStub("J<a!"), WebUtil.decryptStringStub("A#k="))));
    }

    @Override
    public void reset(Web web) {

    }

}

