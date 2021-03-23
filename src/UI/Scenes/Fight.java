package UI.Scenes;

import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Label;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class Fight extends GameScene {
    private Panel menu, panelInfo;
    private Scene floorMenu, inventory;
    public Fight() {
        menu = new Panel();
        panelInfo = new Panel();

        Button attack = new Button();
        attack.setText("Attack");
        attack.addClickListener((InteractiveControl control) -> attack(control));
        attack.addClickListener(this::refreshPanelInfo);

        Button inventory = new Button();
        inventory.setText("Inventory");
        inventory.addClickListener((InteractiveControl control) -> displayInventory(control));
        inventory.addClickListener(this::refreshPanelInfo);

        Button nextTurn = new Button();
        nextTurn.setText("Next turn");
        nextTurn.addClickListener((InteractiveControl control) -> nextTurn(control));
        nextTurn.addClickListener(this::refreshPanelInfo);

        panelInfo.setHeading("Enemies");
        menu.setHeading("Fight");

        menu.addControl(attack);
        menu.addControl(inventory);
        menu.addControl(nextTurn);

        menu.setX(50);
        menu.setY(5);

        panelInfo.setX(menu.getX() + menu.getWidth() + 5);
        panelInfo.setY(menu.getY());

        menu.setRedraw(true);
        panelInfo.setRedraw(true);

        addControl(menu);
        addControl(panelInfo);

        refreshPanelInfo(null);
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
            setCurrentScene(false);
            SceneContainer.getScene("TheEnd").setCurrentScene(true);
            return;
        }
    }

    @Override
    public void draw() {
        refreshPanelInfo(null);
        super.draw();
    }

    private void refreshPanelInfo(InteractiveControl control){
        panelInfo.clear();
        String enemies = "";
        int counter = 1;
        for (var enemy :
                playerController.getEnemies()) {
            enemies += counter + ". " + enemy + "\n";
            counter++;
        }
        Label label = new Label();
        label.setText(enemies);
        panelInfo.addControl(label);
    }
}
