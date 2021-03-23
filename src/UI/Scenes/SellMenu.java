package UI.Scenes;

import Controllers.ItemInformation;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class SellMenu extends GameScene {
    private Panel menu;
    private Scene shop;
    public SellMenu() {
        menu = new Panel();
        menu.setHeading("Inventory");


        menu.setY(5);
        menu.setX(50);
        menu.setRedraw(true);

        refreshItems();
    }

    @Override
    public void draw() {
        if(menu.getControls().size() - 1 != playerController.getItems().length)
            refreshItems();
        super.draw();
    }

    public void refreshItems(){
        menu.clear();
        clearControls();

        Button back = new Button();
        back.setText("Back");
        back.addClickListener((InteractiveControl control) -> back(control));
        menu.addControl(back);

        for (ItemInformation item : playerController.getItems()) {
            Button button = new Button();
            button.setText(item.getName());
            button.setItemInformation(item);
            button.addClickListener(this::sell);
            menu.addControl(button);
        }
        addControl(menu);
    }

    private void back(InteractiveControl control){
        if(shop == null) shop = SceneContainer.getScene("Shop");
        setCurrentScene(false);
        shop.setCurrentScene(true);
    }

    private void sell(InteractiveControl control){
        shopController.sell(((Button) control).getItemInformation());
    }
}
