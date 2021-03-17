package GachiCore.GameHandlers.Floors;

import GachiCore.AI.AIUser;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.HeterosexualBuilder;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;
import GachiCore.GameHandlers.GachiHandler;

import java.util.ArrayList;

public class FirstFloorRight implements FloorBuilder {
    private GachiHandler gachiHandler = GachiHandler.getInstance();


    @Override
    public Floor getFloor() {
        AIBuilder builder = new HeterosexualBuilder();
        ArrayList<AIUser> bots = new ArrayList<>();
        for (int index = 0; index < 5; index++){
            bots.add(builder.build(gachiHandler.getHero()));
        }
        FloorEnemies floorEnemies = new FloorEnemies(gachiHandler.getHero(), bots.toArray(new AIUser[bots.size()]));
        return new Floor(gachiHandler.getHero(), floorEnemies);
    }
}
