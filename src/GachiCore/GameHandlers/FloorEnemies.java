package GachiCore.GameHandlers;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.AI.AIUser;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Inventory;
import GachiCore.Entities.Base.Entity;
import GachiCore.Entities.Base.GachiPowerUser;

import java.util.ArrayList;
import java.util.Arrays;

public class FloorEnemies {
    private ArrayList<AIUser> bots = new ArrayList<>();
    private ArrayList<AIUser> aiForRemove = new ArrayList<>();
    private ArrayList<Inventory> inventories = new ArrayList<>();
    private GachiPowerUser hero;
    private final MessageBox messageBox = MessageBox.getInstance();
    private Message itemFound = new Message("Item found: ", MessageType.POSITIVE);
    private boolean needRefresh = false;

    public FloorEnemies(GachiPowerUser gachiPowerUser, AIUser... aiUsers) {
        hero = gachiPowerUser;
        Arrays.stream(aiUsers).forEach((AIUser bot) -> addBot(bot));
        for (AIUser user : bots) {
            ((Entity) user).addActionAfterDeath((Entity entity) -> removeBot(user));
            inventories.add(((Entity) user).getInventory());
        }
        bots.get(0).setVanguard(true);
        gachiPowerUser.setEnemy((Entity) bots.get(0));
    }

    public void addBot(AIUser aiUser){
        bots.add(aiUser);
    }

    public void removeBot(AIUser removeASSer){
        aiForRemove.add(removeASSer);
        needRefresh = true;
        refreshVanguard();
    }

    public void refreshVanguard(){
        for (var bot :
                bots) {
            bot.setVanguard(false);
            if(((Entity) bot).isAlive()){
                bot.setVanguard(true);
                hero.setEnemy((Entity) bot);
                return;
            }
        }
    }

    public void newTurn(){
        if(isClear()) {
            end();
            return;
        }
        if(needRefresh){
            refresh();
        }
        bots.forEach((AIUser bot) -> bot.newTurn());
    }

    private void refresh(){
        for (AIUser user : aiForRemove) {
            bots.remove(user);
        }
        aiForRemove.clear();
        if(isClear()) {
            end();
            return;
        }
        hero.setEnemy((Entity) bots.get(0));
    }

    public void turn(){
        if(isClear()) {
            end();
            return;
        }
        if(needRefresh){
            refresh();
        }
        if( bots.size() >= 2){
            botHandler(bots.get(0).wantSwap(), bots.get(1).wantAgreeWithSwap());
        }
        for (AIUser bot : bots) {
            if(!hero.isAlive()) return;
            bot.turn();
        }
    }

    public void nextTurn(){
        if(isClear()) {
            end();
            return;
        }

        if(needRefresh){
            refresh();
        }
        bots.forEach((AIUser bot) -> bot.nextTurn());
    }

    private void botHandler(boolean vanguardAnswer, boolean secondBotAnswer){
        if(vanguardAnswer && secondBotAnswer){
            ArrayList<AIUser> newOrder = new ArrayList<>(bots.subList(1, bots.size()));
            newOrder.add(bots.get(0));
            bots.get(0).setVanguard(false);
            bots.get(0).positionChanged();
            bots.get(1).setVanguard(true);
            bots = newOrder;

            hero.setEnemy((Entity) bots.get(0));
        }
    }

    public boolean isClear(){
        return bots.isEmpty();
    }

    public void end(){
        for (Inventory inventory : inventories) {
            inventory.setOwner(hero);
            inventory.getEquipments().forEach((Equipment equip) -> addItem(equip));
            inventory.getConsumables().forEach((Consumable consumable) -> addItem(consumable));

            hero.getInventory().addMoney(inventory.getMoney());
        }
    }

    private void addItem(Item item){
        hero.getInventory().addItem(item);
        messageBox.addNewMessage(new Message(item.getDescription() + item.getName(), MessageType.POSITIVE));
    }

}
