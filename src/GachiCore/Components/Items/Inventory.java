package GachiCore.Components.Items;

import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Entities.Base.Entity;

import java.util.ArrayList;

public class Inventory {
    private Entity owner;
    private Equipment armor;
    private Equipment weapon;
    private ArrayList<Equipment> equipments = new ArrayList<>();
    private ArrayList<Consumable> consumables = new ArrayList<>();

    private int money;

    public void addItem(Item item){
        if(item == null) return;
        item.setOwner(owner);
        if(item instanceof Equipment)
            equipments.add((Equipment) item);
        else
        {
            if(item instanceof Consumable)
                consumables.add((Consumable) item);
         }
    }

    public void removeItem(Item item){
        if(item instanceof Equipment && equipments.contains((Equipment) item)) {
            equipments.remove((Equipment) item);
            takeOff((Equipment) item);
        }
        else
        {
            if(item instanceof Consumable && consumables.contains((Consumable) item))
                consumables.remove((Consumable) item);
        }
    }

    public void takeOn(Equipment equipment){
        if(equipment == null) return;
        if(equipment.getEquipmentType() == EquipmentType.ARMOR){
            if(armor != null){
                armor.takeOff();
            }
            armor = equipment;
        }

        if(equipment.getEquipmentType() == EquipmentType.WEAPON){
            if(weapon != null){
                weapon.takeOff();
            }
            weapon = equipment;
        }

        equipment.takeOn();
    }

    public void use(Consumable consumable){
        consumable.use();
    }

    public boolean have(Item item){
        boolean equipmentItem = equipments.contains(item);
        boolean consumableItem = consumables.contains(item);
        return equipmentItem || consumableItem;
    }

    public void takeOff(Equipment equipment){
        if(equipment == armor){
            armor.takeOff();
            armor = null;
        }
        if(equipment == weapon){
            weapon.takeOff();
            weapon = null;
        }
    }

    public void addMoney(int count){
        money += count;
    }

    public void minusMoney(int count){
        if(!isEnoughMoney(count)) return;
        money -= count;
    }

    public boolean isEnoughMoney(int price){
        return money - price >= 0;
    }


    public void setOwner(Entity owner) {
        this.owner = owner;
        equipments.forEach((Equipment equip) -> equip.setOwner(owner));
        consumables.forEach((Consumable consumable) -> consumable.setOwner(owner));
    }

    //region Getters


    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public ArrayList<Consumable> getConsumables() {
        return consumables;
    }

    public int getMoney() {
        return money;
    }

    public Equipment getArmor() {
        return armor;
    }

    public Equipment getWeapon() {
        return weapon;
    }
    //endregion
}
