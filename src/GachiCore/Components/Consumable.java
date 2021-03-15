package GachiCore.Components;

import GachiCore.Entities.Base.Entity;

public abstract class Consumable extends Item{
    public Consumable(Entity owner, String name, String description, int purchasePrice, int sellingPrice) {
        super(owner, name, description, purchasePrice, sellingPrice);
    }
    public void use(){
        buffs.forEach((Buff buff) -> buff.addBuff());
        owner.getInventory().removeItem(this);
    }
}
