package Core.Components.Base.Usable;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Core.Components.Base.Buff;
import Core.Components.Base.Inventory;
import Core.Components.Base.Quality;
import Core.EntitiesOLD.Base.Entity;

public abstract class Item {
    protected Entity target;

    protected Inventory inventory;
    protected Quality quality;
    protected Buff buff;
    protected MessageBox messageBox = MessageBox.getInstance();
    protected String name, description;
    protected int purchasePrice, sellingPrice;
    protected Message itemFound;
    protected Message itemBought;
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

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void itemFound(){
        messageBox.addNewMessage(itemFound);
    }

    public void itemBought(){
        messageBox.addNewMessage(itemBought);
    }

    public abstract boolean canUse();
    public abstract void use();
}