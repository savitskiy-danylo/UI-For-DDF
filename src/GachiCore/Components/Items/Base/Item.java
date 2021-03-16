package GachiCore.Components.Items.Base;

import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Entities.Base.Entity;

import java.util.ArrayList;

public abstract class Item {
    protected Entity owner;
    private String name, description;
    private int purchasePrice, sellingPrice;
    protected ArrayList<Buff> buffs = new ArrayList<>();

    public Item(String name, String description, int purchasePrice, int sellingPrice) {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
        buffs.forEach((Buff buff) -> buff.setTarget(owner));
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
}
