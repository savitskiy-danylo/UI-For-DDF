package GachiCore.Components.Items.Equipment.Base;

import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Entities.Base.Entity;

public abstract class Equipment extends Item {
    private EquipmentType equipmentType;
    private Quality quality;

    public Equipment(String name, String description, int purchasePrice, int sellingPrice, EquipmentType equipmentType) {
        super(name, description, purchasePrice, sellingPrice);
        this.equipmentType = equipmentType;
    }

    public void takeOn(){
        buffs.forEach((Buff buff) -> buff.addBuff());
    }
    public void takeOff(){
        buffs.forEach((Buff buff) -> buff.removeBuff());
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public Quality getQuality() {
        return quality;
    }
}
