package UI.Scenes;

import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.GameScene;
import UI.Scenes.Base.Scene;

public class Shop extends GameScene {
    private Panel menu;
    private Scene floorMenu, buyMenu, sellMenu;
    public Shop() {
        menu = new Panel();

        Button buy = new Button();
        buy.setText("Buy");
        buy.addClickListener(this::buy);

        Button sell = new Button();
        sell.setText("Sell");
        sell.addClickListener(this::sell);

        Button back = new Button();
        back.setText("Back");
        back.addClickListener(this::back);

        menu.addControl(buy);
        menu.addControl(sell);
        menu.addControl(back);

        menu.setHeading("Shop");
        menu.setX(50);
        menu.setY(5);

        menu.setRedraw(true);
        addControl(menu);
    }

    private void back(InteractiveControl control) {
        if(floorMenu == null) floorMenu = SceneContainer.getScene("FloorMenu");
        setCurrentScene(false);
        floorMenu.setCurrentScene(true);
    }

    @Override
    public void setCurrentScene(boolean currentScene) {
        if(currentScene == false) {
            super.setCurrentScene(false);
            return;
        }
        if(!shopController.haveShop()){
            if(floorMenu == null) floorMenu = SceneContainer.getScene("FloorMenu");
            setCurrentScene(false);
            floorMenu.setCurrentScene(true);
            return;
        }
        super.setCurrentScene(true);
    }

    private void sell(InteractiveControl control){
        if(sellMenu == null) sellMenu = SceneContainer.getScene("SellMenu");
        setCurrentScene(false);
        sellMenu.setCurrentScene(true);
    }

    private void buy(InteractiveControl control){
        if(buyMenu == null) buyMenu = SceneContainer.getScene("BuyMenu");
        setCurrentScene(false);
        buyMenu.setCurrentScene(true);
    }
}
