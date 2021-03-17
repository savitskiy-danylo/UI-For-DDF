package GachiCore.GameHandlers.Floors;

import GachiCore.AI.AIUser;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.HeterosexualBuilder;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;
import GachiCore.GameHandlers.GachiHandler;

public class FirstFloorRight implements FloorBuilder {
    private GachiHandler gachiHandler = GachiHandler.getInstance();


    @Override
    public Floor getFloor() {
        AIBuilder builder = new HeterosexualBuilder();
        AIUser monster = builder.build(gachiHandler.getHero());
        FloorEnemies floorEnemies = new FloorEnemies(gachiHandler.getHero(), monster);
        return new Floor(gachiHandler.getHero(), floorEnemies);
    }
}
