package Core;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items = new ArrayList<>();
    private final MessageBox messageBox = MessageBox.getInstance();
    protected Entity owner;

    public Inventory(Entity owner){
        this.owner = owner;
    }

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public void addItemLoot(Item item){
        items.add(item);
        item.itemFound();
    }

    public void addNewItem(Item item){
        items.add(item);
        item.itemBought();
    }

    public void addItemLoot(Inventory inventory){
        for (var item :
                inventory.getItems()) {
            addItemLoot(item);
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
