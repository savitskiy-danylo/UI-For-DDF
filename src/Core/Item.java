package Core;

public abstract class Item {
    private Entity target;
    private Inventory inventory;
    private Quality quality;
    private String name, description;
    private int purchasePrice, sellingPrice;

    public Item(Entity target) {
        this.target = target;

    }

    public Quality getQuality() {
        return quality;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public abstract boolean canUse();
    public abstract void use();
}