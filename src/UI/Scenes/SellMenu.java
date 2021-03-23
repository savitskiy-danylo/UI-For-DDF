package UI.Scenes;

import Controllers.ItemInformation;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Label;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class SellMenu extends GameScene {
    private Panel menu, panelInfo;
    private Button back;
    private ItemInformation[] items;
    private Scene shop;

    public SellMenu() {
        menu = new Panel();
        panelInfo = new Panel();

        back = new Button();
        back.setText("Back");
        back.addClickListener(this::back);
        back.addFocusChangedListener(this::backFocus);

        menu.setHeading("Sell");
        menu.setX(50);
        menu.setY(5);

        menu.setRedraw(true);
        panelInfo.setRedraw(true);
        addControl(panelInfo);
        addControl(menu);
    }

    @Override
    public void draw() {
        refreshItems();
        super.draw();
    }

    public void refreshItems(){
        if(items == null || items.length != playerController.getItems().length) {
            items = playerController.getItems();
            menu.clear();
            clearControls();
            for (var item :
                    items) {
                Button button = new Button();
                button.setText(item.getName());
                button.setItemInformation(item);
                button.addClickListener(this::sell);
                button.addFocusChangedListener(this::refreshPanelInfo);
                menu.addControl(button);
            }
            menu.addControl(back);
            addControl(menu);
            setFocusControl(back);
        }
    }

    @Override
    public void setCurrentScene(boolean currentScene) {
        super.setCurrentScene(currentScene);
        refreshItems();
    }

    public void back(InteractiveControl control){
        if(shop == null) shop = SceneContainer.getScene("Shop");
        setCurrentScene(false);
        shop.setCurrentScene(true);
    }

    public void refreshPanelInfo(InteractiveControl control){
        panelInfo.clear();
        Button button = (Button) control;

        panelInfo.setHeading(button.getItemInformation().getName());
        Label label = new Label();
        label.setText(button.getItemInformation().getDescription() + "\n" +
                "Price: " + button.getItemInformation().getSellingPrice());
        panelInfo.addControl(label);
        panelInfo.setX(menu.getX() + menu.getWidth() + 5);
        panelInfo.setY(menu.getY());
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

    private void sell(InteractiveControl control){
        shopController.sell(((Button) control).getItemInformation());
    }
}
