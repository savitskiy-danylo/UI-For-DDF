package Core.Components.Base;

import Core.Components.Base.Usable.Equipment;
import Core.Components.PlusArmor;
import Core.Entity;

public class LatexCostume extends Equipment {


    public LatexCostume(Entity target) {
        super(target);
        name = "LatexWish";
        buff = new PlusArmor(target, 5);

    }

    @Override
    public void takeOff() {
        target.removeBuff(buff);

    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        target.addBuff(buff);


    }
}
