package Core;

public abstract class Item {
    private Entity target;
    private Inventory inventory;

    public Item(Entity target) {
        this.target = target;

    }

    public abstract boolean canUse();
    public abstract void use();
}