package GachiCore.GameHandlers;

import GachiCore.AI.AIUser;
import GachiCore.Components.Items.Inventory;
import GachiCore.Entities.Base.Entity;
import GachiCore.Entities.Base.GachiPowerUser;

import java.util.ArrayList;
import java.util.Arrays;

public class FloorEnemies {
    private ArrayList<AIUser> bots = new ArrayList<>();
    private ArrayList<AIUser> aiForRemove = new ArrayList<>();
    private ArrayList<Inventory> inventories = new ArrayList<>();
    private int money = 0;
    private GachiPowerUser hero;
    private boolean needRefresh = false;
    private Runnable actionAfterDeath;
    public FloorEnemies(GachiPowerUser gachiPowerUser, AIUser... aiUsers) {
        hero = gachiPowerUser;
        Arrays.stream(aiUsers).forEach((AIUser bot) -> addBot(bot));
        for (AIUser user : bots) {
            ((Entity) user).addActionAfterDeath((Entity entity) -> removeBot(user));
            inventories.add(((Entity) user).getInventory());
            money += ((Entity) user).getInventory().getMoney();
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
        actionAfterDeath.run();
    }

    public void newTurn(){
        if(needRefresh){
            refresh();
        }
        if(isClear()) actionAfterDeath.run();
        bots.forEach((AIUser bot) -> bot.newTurn());
    }

    private void refresh(){
        for (AIUser user : aiForRemove) {
            bots.remove(user);
        }
        aiForRemove.clear();
        if(isClear()) actionAfterDeath.run();
        hero.setEnemy((Entity) bots.get(0));
    }

    public void turn(){
        if(isClear()) actionAfterDeath.run();
        if(needRefresh){
            refresh();
        }
        if( bots.size() >= 2){
            botHandler(bots.get(0).wantSwap(), bots.get(1).wantSkip());
        }
        for (AIUser bot : bots) {
            if(!hero.isAlive()) return;
            bot.turn();
        }
    }

    public void nextTurn(){
        if(isClear()) actionAfterDeath.run();
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

    public void addActionAfterDefeat(Runnable run){
        actionAfterDeath = run;
    }

    public boolean isClear(){
        return bots.isEmpty();
    }

    public ArrayList<Inventory> getInventories() {
        return inventories;
    }

    public int getMoney() {
        return money;
    }
}
