package UI.Scenes;

import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;

public class Inventory extends GameScene {
    private Panel menu, informationPanel;
    public Inventory() {
        menu = new Panel();
        informationPanel

        menu.setRedraw(true);
        menu.setHeading("Inventory");
    }
}
