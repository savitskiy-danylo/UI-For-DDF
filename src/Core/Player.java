package Core;

import Core.Components.Base.Inventory;
import Core.Entity;

public class Player extends Entity {
    public Player(){
        super(1, 2, 100, 1, 2,
                80, 10, 15, 1, 4,
                1000, 3, 1);
        setPosition(1);
        setPriceOfAttack(2);
        inventory = new Inventory(this);
    }
}
