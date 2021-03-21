package GachiCore.GameHandlers.Floors.Base;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.GachiHandler;

import java.util.ArrayList;

public class Shop {
    private final ArrayList<Item> items = new ArrayList<>();
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private final GachiPowerUser hero = gachiHandler.getHero();
    private final MessageBox messageBox = MessageBox.getInstance();
    private final Message itemBought = new Message("Item purchased: ", MessageType.POSITIVE);
    public Shop(Item item1, Item item2, Item item3) {
        items.add(item1);
        items.add(item2);
        items.add(item3);
    }

    public void buy(Item item){
        if(canBuy(item)){
            hero.getInventory().addItem(item);
            hero.getInventory().minusMoney(item.getPurchasePrice());
            messageBox.addNewMessage(new Message(itemBought.getText() + item.getName(), MessageType.POSITIVE));
            items.remove(item);
        }
    }

    public void sell(Item item){
        hero.getInventory().removeItem(item);
        hero.getInventory().addMoney(item.getSellingPrice());
    }

    public boolean canBuy(Item item){
        return hero.getInventory().isEnoughMoney(item.getPurchasePrice());
    }

    public Item[] getItems(){
        return items.toArray(new Item[items.size()]);
    }
}
