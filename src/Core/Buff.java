package Core;

public abstract class Buff {
    protected final Entity target;

    public Buff(Entity target) {
        this.target = target;
    }
    public abstract void use();
}
