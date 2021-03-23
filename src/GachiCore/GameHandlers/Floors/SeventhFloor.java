package GachiCore.GameHandlers.Floors;

import GachiCore.Builders.*;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.Floors.Base.FloorBuilder;

public class SeventhFloor implements FloorBuilder {
    @Override
    public Floor getFloor() {
        FloorEnemies floorEnemies = new FloorEnemies();
        for (int index = 0; index < 4; index++){
            floorEnemies.addBot(getRandomBuilder(
                    new YandereGirlBuilder(), new SWATManBuilder(), new GymManBuilder())
                    .build(getEnemy()));
        }
        return new Floor(floorEnemies, getRandomShop());
    }
}
