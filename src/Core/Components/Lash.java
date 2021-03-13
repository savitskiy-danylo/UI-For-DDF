package Core.Components;

import Core.Components.Base.Usable.Equipment;
import Core.Entity;

public class Lash extends Equipment {
    {
        name = "Lash";
    }

    public Lash(Entity target) {
        super(target);
        buff = new PlusDamage(target, 5);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        target.addBuff(buff);
    }

    @Override
    public void takeOff() {
        target.removeBuff(buff);
    }
}
