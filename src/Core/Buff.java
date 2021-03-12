package Core;

public abstract class Buff {
    protected final Entity target, causer;

    public Buff(Entity target, Entity causer) {
        this.target = target;
        this.causer = causer;
    }
    public abstract void nextTurn();
}
