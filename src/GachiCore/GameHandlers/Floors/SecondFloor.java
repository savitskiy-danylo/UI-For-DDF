package GachiCore.GameHandlers.Floors;

import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.SkeletonBuilder;
import GachiCore.Builders.SlaveBuilder;
import GachiCore.Builders.SlimeBuilder;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;

import java.util.Random;

public class SecondFloor implements FloorBuilder {

    @Override
    public Floor getFloor() {
        AIBuilder slimeBuilder = new SlimeBuilder();
        AIBuilder skeletonBuilder = new SkeletonBuilder();
        AIBuilder slaveBuilder = new SlaveBuilder();
        Random random = new Random();
        FloorEnemies floorEnemies = new FloorEnemies();
        for (int index = 0; index < 4; index++){
            switch (random.nextInt(3)){
                case 0 -> floorEnemies.addBot(slimeBuilder.build(getEnemy()));
                case 1 -> floorEnemies.addBot(skeletonBuilder.build(getEnemy()));
                case 2 -> floorEnemies.addBot(slaveBuilder.build(getEnemy()));
            }
        }
        return new Floor(floorEnemies);
    }
}
