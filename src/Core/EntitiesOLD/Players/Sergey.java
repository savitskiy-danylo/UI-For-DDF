package Core.EntitiesOLD.Players;

import Core.EntitiesOLD.Base.Player;

public class Sergey extends Player {
    public Sergey(){
        super(0,1, 100, 2, 2,
                80, 10, 15, 2,
                4, 1000, 3, 1);
        setName("Sergey");
        setPosition(1);
        setPriceOfAttack(2);
    }
}
