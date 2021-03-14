package Core.Components;

import Core.Components.Base.Buff;
import Core.EntitiesOLD.Base.Entity;

public class PlusArmor extends Buff {
    private int armor;

    public PlusArmor(Entity target, int armor) {
        super(target);
        this.armor = armor;

    }

    @Override
    public void use() {
        target.addArmor(armor);

    }
}
