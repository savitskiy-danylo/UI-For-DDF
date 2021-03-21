package GachiCore.GameHandlers.Floors.Base;

import GachiCore.Entities.Base.Entity;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.GachiHandler;

public interface FloorBuilder {
    public default Entity getEnemy(){
        return GachiHandler.getInstance().getHero();
    }
    public Floor getFloor();
}
