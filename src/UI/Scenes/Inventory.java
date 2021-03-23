package UI.Scenes;

import Controllers.ItemInformation;
import GachiCore.Components.Items.Base.Item;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Label;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

import java.util.ArrayList;

public class Inventory extends GameScene {
    private Panel menu, panelInfo;
    private Button back;
    private Scene caller;
    private final ArrayList<ItemInformation> itemInformations = new ArrayList<>();
    public Inventory() {
        menu = new Panel();
        panelInfo = new Panel();

        Label label = new Label();
        label.setText("Kostil");
        panelInfo.addControl(label);

        back = new Button();
        back.setText("Back");
        back.addClickListener((InteractiveControl control) -> back(control));
        back.addFocusChangedListener(this::backFocus);
        menu.setHeading("Inventory");


        menu.setY(5);
        menu.setX(50);
        menu.setRedraw(true);

        refreshItems();
        addControl(panelInfo);
    }

    public void refreshPanelInfo(InteractiveControl control){
        panelInfo.clear();
        Button button = (Button) control;

        panelInfo.setHeading(button.getItemInformation().getName());
        Label label = new Label();
        label.setText(button.getItemInformation().getDescription() + "\n" +
                "Price: " + button.getItemInformation().getPurchasingPrice());
        panelInfo.addControl(label);
        panelInfo.setX(menu.getX() + menu.getWidth() + 5);
        panelInfo.setY(menu.getY());
    }

    @Override
    public void draw() {

        if(menu.getControls().size() - 1 != playerController.getItems().length) {
            refreshItems();
        }else {
            ArrayList<ItemInformation> currentItems = (ArrayList<ItemInformation>) itemInformations.clone();
            ItemInformation[] playersItems = playerController.getItems().clone();
            for (var item :
                    currentItems) {
                if(!playersItems[currentItems.indexOf(item)].getName().equals(item.getName())) {
                    refreshItems();
                    break;
                }
            }
        }
        super.draw();
    }

    public void refreshItems(){
        menu.clear();
        itemInformations.clear();
        clearControls();

        menu.addControl(back);

        for (ItemInformation item : playerController.getItems()) {
            Button button = new Button();
            button.setText(item.getName());
            button.setItemInformation(item);
            itemInformations.add(item);
            button.addClickListener((InteractiveControl control) -> use(control));
            button.addFocusChangedListener(this::refreshPanelInfo);
            menu.addControl(button);
        }
        addControl(menu);
    }

    private void back(InteractiveControl control){
        setCurrentScene(false);
        caller.setCurrentScene(true);
    }

    public void setCaller(Scene scene){
        caller = scene;
    }

    private void use(InteractiveControl control){
        playerController.useItem(((Button) control).getItemInformation());
    }

    public void backFocus(InteractiveControl control){
        if(control.isFocus()) {
            panelInfo.setRedraw(false);
            refreshAutoRedrawControls();
        }
        else {
            panelInfo.setRedraw(true);
            addControl(panelInfo);
        }
        super.draw();
    }
}
