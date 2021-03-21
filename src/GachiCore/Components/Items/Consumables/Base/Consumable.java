package GachiCore.Components.Items.Consumables.Base;

import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Equipment.Base.Quality;
import GachiCore.Entities.Base.Entity;

public abstract class Consumable extends Item {
    public Consumable(String name, String description, int purchasePrice, int sellingPrice) {
        super(name, description, purchasePrice, sellingPrice);
    }
    public void use(){
        buffs.forEach((Buff buff) -> buff.addBuff());
        owner.getInventory().removeItem(this);
    }
    private Quality quality;

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }
}
