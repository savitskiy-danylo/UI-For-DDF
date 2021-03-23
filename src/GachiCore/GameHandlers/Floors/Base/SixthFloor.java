package GachiCore.GameHandlers.Floors.Base;

import GachiCore.Builders.MinotourBuilder;
import GachiCore.Builders.PewManBuilder;
import GachiCore.Builders.SpearManBuilder;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;

import java.util.Random;

public class SixthFloor implements FloorBuilder{
    @Override
    public Floor getFloor() {
        FloorEnemies floorEnemies = new FloorEnemies();
        for (int index = 0; index < 4; index++){
            floorEnemies.addBot(getRandomBuilder( new Random(),
                    new PewManBuilder(), new MinotourBuilder(), new SpearManBuilder())
                    .build(getEnemy()));
        }
        return new Floor(floorEnemies, getRandomShop(new Random(6)));
    }
}
