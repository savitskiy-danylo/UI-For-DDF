package Core.Components.Base.Usable;

import Core.Entity;

public abstract class Equipment extends Item{

    public Equipment(Entity target) {
        super(target);
    }

    public abstract void takeOff();
}
