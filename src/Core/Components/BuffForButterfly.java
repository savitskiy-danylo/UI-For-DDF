package Core.Components;

import Core.Components.Base.Buff;
import Core.Entity;

public class BuffForButterfly extends Buff {
    private int damage, dodges;
    public BuffForButterfly(Entity target, int damage, int dodges) {
        super(target);
        this.damage = damage;
        this.dodges = dodges;
    }

    @Override
    public void use() {
        target.addCurrentDamage(4);
        target.addAgility(15);
    }
}
