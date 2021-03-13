package Core.Components.Base;

import Core.Entity;

public abstract class Buff {
    protected final Entity target;

    public Buff(Entity target) {
        this.target = target;
    }
    public abstract void use();
}
