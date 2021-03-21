package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.BrokenSwordBuff;
import GachiCore.Components.Buffs.StickBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class Stick extends Equipment {
    public Stick(){
        super("Stick", "woody",25, 15, EquipmentType.WEAPON);
        setQuality(Quality.ONE);
        buffs.add(new StickBuff());
    }

}
