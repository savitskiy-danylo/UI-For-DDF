package Core;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items = new ArrayList<>();
    private final MessageBox messageBox = MessageBox.getInstance();

    public void addItem(Item item){
        items.add(item);
        item.itemFound();
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
