package UI.Scenes;

import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class Fight extends GameScene {
    private Panel menu;
    private Scene floorMenu;
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

        menu.setHeading("Fight");

        menu.addControl(attack);
        menu.addControl(inventory);
        menu.addControl(skills);

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
            setCurrentScene(false);
            floorMenu.setCurrentScene(true);
        }
    }

    private void displayInventory(InteractiveControl control){
        //TODO DISPLAY INVENTORY
    }
}
