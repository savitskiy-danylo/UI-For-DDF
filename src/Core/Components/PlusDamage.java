package Core.Components;

import Core.Components.Base.Buff;
import Core.EntitiesOLD.Base.Entity;

public class PlusDamage extends Buff {
    private int damage;
    public PlusDamage(Entity target, int damage) {
        super(target);
        this.damage = damage;
    }

    @Override
    public void use() {
        target.addCurrentDamage(damage);
    }
}
