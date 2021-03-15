package GachiCore.Components;

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
        Equipment equipment = (Equipment) item;
        if(equipment != null)
            equipments.add(equipment);
        else
        {
            Consumable consumable = (Consumable) item;
            if(consumable != null)
                consumables.add(consumable);
         }
    }

    public void removeItem(Item item){
        Equipment equipment = (Equipment) item;
        if(equipment != null && equipments.contains(equipment))
            equipments.remove(equipment);
        else
        {
            Consumable consumable = (Consumable) item;
            if(consumable != null && consumables.contains(consumable))
                consumables.remove(consumable);
        }
    }

    public void takeOn(Equipment equipment){
        if(equipment.getEquipmentType() == EquipmentType.ARMOR){
            if(armor != null){
                armor.takeOff();
            }
            armor = equipment;
        }else {
            if(weapon != null){
                weapon.takeOff();
            }
            weapon = equipment;
        }
        equipment.takeOn();
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
    }

    //region Getters
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
