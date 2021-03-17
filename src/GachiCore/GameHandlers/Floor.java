package GachiCore.GameHandlers;

import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Inventory;
import GachiCore.Entities.Base.GachiPowerUser;

public class Floor {
    private int money;
    private Inventory loot;

    private GachiPowerUser hero;
    private FloorEnemies enemies;
    private Shop shop;
    private boolean end = false;

    public Floor(GachiPowerUser hero, FloorEnemies enemies) {
        this(0, new Inventory(), hero, enemies);
    }

    public Floor(int money, Inventory loot, GachiPowerUser hero, FloorEnemies enemies) {
        this(money, loot, hero, enemies, null);
    }

    public Floor(int money, Inventory loot, GachiPowerUser hero, FloorEnemies enemies, Shop shop) {
        this.money = money;
        this.loot = loot;
        this.hero = hero;
        this.enemies = enemies;
        this.shop = shop;
        fillInventories();
        enemies.addActionAfterDefeat(() -> end());
    }

    private void takeMoney(){
        money += enemies.getMoney();
    }

    private void fillInventories(){
        enemies.getInventories().forEach((Inventory inventory) -> fillInventory(inventory));
    }

    private void fillInventory(Inventory from){
        from.getConsumables().forEach((Consumable con) -> loot.addItem(con));
        from.getEquipments().forEach((Equipment equip) -> loot.addItem(equip));
    }

    public void turn(){
        if(!end)
            enemies.turn();
    }

    public void nextTurn(){
        if(!end)
            enemies.nextTurn();
    }

    public void newTurn(){
        if(!end)
            enemies.newTurn();
    }

    private void end(){
        end = true;
        hero.getInventory().addMoney(money);
        loot.getConsumables().forEach((Consumable con) -> hero.getInventory().addItem(con));
        loot.getEquipments().forEach((Equipment equipment) -> hero.getInventory().addItem(equipment));
    }

    public boolean isClear(){
        return end;
    }

}
