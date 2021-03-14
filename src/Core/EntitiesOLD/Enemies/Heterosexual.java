package Core.EntitiesOLD.Enemies;

import Core.EntitiesOLD.Base.Enemy;

public class Heterosexual extends Enemy {
    public Heterosexual(){
        super(0,0, 15, 0, 0,
                0, 1, 5, 1,
                4, 0, 0, 1);
        setName("Simple heterosexual");
        setPosition(1);
        setPriceOfAttack(2);
    }

    @Override
    public boolean retreat() {
        return false;
    }
}
