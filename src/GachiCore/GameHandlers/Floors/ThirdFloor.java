package GachiCore.GameHandlers.Floors;

import GachiCore.Builders.SkeletonBuilder;
import GachiCore.Builders.SlaveBuilder;
import GachiCore.Builders.SlimeBuilder;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;

import java.util.Random;

public class ThirdFloor implements FloorBuilder {
    @Override
    public Floor getFloor() {
        FloorEnemies floorEnemies = new FloorEnemies();
        for (int index = 0; index < 4; index++){
            floorEnemies.addBot(getRandomBuilder( new Random(),
                    new SkeletonBuilder(), new SlaveBuilder())
                    .build(getEnemy()));
        }
        return new Floor(floorEnemies, getRandomShop(new Random(5)));
    }
}
