package Core.EntitiesOLD.Players;

import Core.EntitiesOLD.Base.Player;

public class Oleg extends Player {
    public Oleg() {
        super(2,2, 100, 0, 1,
                60, 10, 15, 2,
                4, 1000, 4, 1);
        setName("Oleg");
        setPosition(1);
        setPriceOfAttack(2);
    }
}
