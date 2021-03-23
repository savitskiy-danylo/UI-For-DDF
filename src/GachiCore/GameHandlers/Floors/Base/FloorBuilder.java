package GachiCore.GameHandlers.Floors.Base;

import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Entities.Base.Entity;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.GachiHandler;

import java.util.Random;

public interface FloorBuilder {
    public default Entity getEnemy(){
        return GachiHandler.getInstance().getHero();
    }
    public Floor getFloor();
    public default AIBuilder getRandomBuilder(AIBuilder... builders){
        Random random = new Random();
        return builders[random.nextInt(builders.length)];
    }
}
