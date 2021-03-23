package GachiCore.GameHandlers;

import GachiCore.Entities.Base.Enemy;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.Floors.Base.Shop;

import java.util.ArrayList;

public class Floor {
    private GachiPowerUser hero = GachiHandler.getInstance().getHero();
    private FloorEnemies enemies;
    private Shop shop;

    public Floor(FloorEnemies enemies) {
        this(enemies, null);
    }

    public Floor(FloorEnemies enemies, Shop shop) {
        this.enemies = enemies;
        this.shop = shop;
    }

    public void turn(){
        if(!isClear())
            enemies.turn();
    }

    public void nextTurn(){
        if(!isClear())
            enemies.nextTurn();
    }

    public void newTurn(){
        if(!isClear())
            enemies.newTurn();
    }

    public boolean isClear(){
        return enemies.isClear();
    }

    public Shop getShop(){ return shop; }

    public Enemy[] getEnemies(){
        return enemies.getEnemies();
    }

}
