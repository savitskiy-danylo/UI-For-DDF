package Core;

public abstract class Item {
    protected Entity target;
    protected Inventory inventory;
    protected Quality quality;
    protected MessageBox messageBox = MessageBox.getInstance();
    protected String name, description;
    protected int purchasePrice, sellingPrice;
    protected Message itemFound = new Message("Item " + name + " found", MessageType.POSITIVE);

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

    public void itemFound(){
        messageBox.addNewMessage(itemFound);
    }
    public abstract boolean canUse();
    public abstract void use();
}