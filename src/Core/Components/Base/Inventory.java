package Core.Components.Base;

import Controllers.MBox.MessageBox;
import Core.Components.Base.Usable.Equipment;
import Core.Components.Base.Usable.Item;
import Core.EntitiesOLD.Base.Entity;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items = new ArrayList<>();
    private final MessageBox messageBox = MessageBox.getInstance();
    protected Entity owner;
    private Equipment currentArmor, currentWeapon;

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

    public void setArmor(Equipment armor){
        if(currentArmor != null) currentArmor.takeOff();
        currentArmor = armor;
        currentArmor.use();
    }

    public void setWeapon(Equipment weapon){
        if(currentWeapon != null) currentWeapon.takeOff();
        currentWeapon = weapon;
        currentWeapon.use();
    }

    public ArrayList<Item> getItems(){ return items; }
}
