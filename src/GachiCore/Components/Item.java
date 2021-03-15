package GachiCore.Components;

import GachiCore.Entities.Base.Entity;

import java.util.ArrayList;

public abstract class Item {
    protected Entity owner;
    private String name, description;
    private int purchasePrice, sellingPrice;
    protected ArrayList<Buff> buffs = new ArrayList<>();

    public Item(Entity owner, String name, String description, int purchasePrice, int sellingPrice) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
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
