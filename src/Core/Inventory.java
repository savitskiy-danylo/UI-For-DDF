package Core;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items = new ArrayList<>();
    private final MessageBox messageBox = MessageBox.getInstance();
    private static final Message newItemMessage = new Message("You have found a new item!", MessageType.POSITIVE);

    public void addItem(Item item){
        messageBox.addNewMessage(newItemMessage);
        items.add(item);
    }

    public void addItem(Inventory inventory){
        for (var item :
                inventory.getItems()) {
            addItem(item);
        }
    }

    public void useItem(String name){
        Item selectedItem = null;
        for (var item :
                items) {
            if(item.getName().equals(name)){
                selectedItem = item;
            }
        }
        selectedItem.use();
    }

    public ArrayList<Item> getItems(){ return items; }
}
