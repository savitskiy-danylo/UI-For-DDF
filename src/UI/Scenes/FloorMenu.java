package UI.Scenes;

import Controllers.PlayerController;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class FloorMenu extends GameScene {
    private Panel menu;
    private Scene inventory, fight, shop;
    private int currentFloor;
    private PlayerController playerController = PlayerController.getInstance();
    public FloorMenu(){
        menu = new Panel();
        currentFloor = playerController.getFloorNumber();

        menu.setHeading("Floor №" + currentFloor);
        Button shop = new Button();

        shop.setText("Shop");
        shop.addClickListener(this::displayShop);

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

    private void displayShop(InteractiveControl control){
        if(shop == null) shop = SceneContainer.getScene("Shop");
        setCurrentScene(false);
        shop.setCurrentScene(true);
    }

    private void displayInventory(InteractiveControl control){
        if(inventory == null) inventory = SceneContainer.getScene("Inventory");
        ((Inventory) inventory).setCaller(this);
        setCurrentScene(false);
        inventory.setCurrentScene(true);
    }

    private void nextFloor(InteractiveControl control){
        if(fight == null) fight = SceneContainer.getScene("Fight");
        if(playerController.isLastFloor()) {
            displayEnd();
            return;
        }
        playerController.nextFloor();
        setCurrentScene(false);
        fight.setCurrentScene(true);
    }

    private void displayEnd() {
        setCurrentScene(false);
        SceneContainer.getScene("TheEnd").setCurrentScene(true);
    }

    @Override
    public void setCurrentScene(boolean currentScene) {
        if(currentFloor != playerController.getFloorNumber())
        {
            currentFloor = playerController.getFloorNumber();
            menu.setHeading("Floor №" + currentFloor);
        }
        super.setCurrentScene(currentScene);
    }
}
