package UI.Scenes;

import Controllers.PlayerController;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;

public class FloorMenu extends GameScene {
    private Panel menu;
    private PlayerController playerController = PlayerController.getInstance();
    public FloorMenu(){
        menu = new Panel();
        menu.setHeading("Floor №" + playerController.getFloorNumber());

        Button shop = new Button();
        shop.setText("Shop");

        Button inventory = new Button();
        inventory.setText("Inventory");
        inventory.addClickListener((InteractiveControl control) -> displayInventory(control));

        Button nextFloor = new Button();
        nextFloor.setText("Next");

        menu.addControl(shop);
        menu.addControl(inventory);
        menu.addControl(nextFloor);

        menu.setX(50);
        menu.setY(5);

        menu.setRedraw(true);
        addControl(menu);
    }

    private void displayInventory(InteractiveControl control){
        //TODO DISPLAY INVENTORY
    }
}
