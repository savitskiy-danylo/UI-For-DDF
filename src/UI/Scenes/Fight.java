package UI.Scenes;

import GachiCore.GameHandlers.SaveHandler;
import Main.StartPoint;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class Fight extends GameScene {
    private Panel menu;
    private Scene floorMenu, inventory;
    public Fight() {
        menu = new Panel();

        Button attack = new Button();
        attack.setText("Attack");
        attack.addClickListener((InteractiveControl control) -> attack(control));

        Button inventory = new Button();
        inventory.setText("Inventory");
        inventory.addClickListener((InteractiveControl control) -> displayInventory(control));

        Button skills = new Button();
        skills.setText("Skills");
        skills.addClickListener((InteractiveControl control) -> attack(control));

        Button nextTurn = new Button();
        nextTurn.setText("Next turn");
        nextTurn.addClickListener((InteractiveControl control) -> nextTurn(control));

        menu.setHeading("Fight");

        menu.addControl(attack);
        menu.addControl(inventory);
        menu.addControl(skills);
        menu.addControl(nextTurn);

        menu.setX(50);
        menu.setY(5);
        menu.setRedraw(true);

        addControl(menu);
    }

    private void displaySkills(InteractiveControl control){
        //TODO DISPLAY SKILLS
    }

    private void attack(InteractiveControl control){
        playerController.attack();
        if(playerController.isClear()){
            displayFloorMenu();
        }
    }

    private void displayFloorMenu(){
        if(floorMenu == null) floorMenu = SceneContainer.getScene("FloorMenu");
        setCurrentScene(false);
        floorMenu.setCurrentScene(true);
    }

    private void displayInventory(InteractiveControl control){
        if(inventory == null) inventory = SceneContainer.getScene("Inventory");
        ((Inventory) inventory).setCaller(this);
        setCurrentScene(false);
        inventory.setCurrentScene(true);
    }

    private void nextTurn(InteractiveControl control){
        playerController.nextTurn();
        if(!playerController.isAlive()){
            System.exit(0);
        }
    }
}
