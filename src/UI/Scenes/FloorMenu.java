package UI.Scenes;

import Controllers.PlayerController;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class FloorMenu extends GameScene {
    private Panel menu;
    private Scene inventory, fight;
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
        nextFloor.addClickListener((InteractiveControl control) -> nextFloor(control));

        menu.addControl(shop);
        menu.addControl(inventory);
        menu.addControl(nextFloor);

        menu.setX(50);
        menu.setY(5);

        menu.setRedraw(true);
        addControl(menu);
    }

    private void displayInventory(InteractiveControl control){
        if(inventory == null) inventory = SceneContainer.getScene("Inventory");
        ((Inventory) inventory).setCaller(this);
        setCurrentScene(false);
        inventory.setCurrentScene(true);
    }

    private void nextFloor(InteractiveControl control){
        if(fight == null) fight = SceneContainer.getScene("Fight");
        playerController.nextFloor();
        setCurrentScene(false);
        fight.setCurrentScene(true);
    }

    @Override
    public void setCurrentScene(boolean currentScene) {
        super.setCurrentScene(currentScene);
        menu.setHeading("Floor №" + playerController.getFloorNumber());
    }
}
