package GachiCore.GameHandlers.Floors.Base;

import GachiCore.AI.Slime;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.SlimeBuilder;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;

public class FirstFloor implements FloorBuilder{

    @Override
    public Floor getFloor() {
        AIBuilder builder = new SlimeBuilder();
        FloorEnemies floorEnemies = new FloorEnemies(builder.build(getEnemy()),
                builder.build(getEnemy()), builder.build(getEnemy()));
        return new Floor(floorEnemies);
    }
}
