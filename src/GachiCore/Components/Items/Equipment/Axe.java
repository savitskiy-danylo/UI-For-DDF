package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.AxeBuff;
import GachiCore.Components.Buffs.HpRegen;
import GachiCore.Components.Buffs.StickBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class Axe extends Equipment {
    public Axe(){
        super("Axe", "For brutal creature's\ndamage + 20;\n" +
                "Berserk: hp every turn + 7", 140, 70, EquipmentType.WEAPON);
        setQuality(Quality.TWO);
        buffs.add(new AxeBuff());
        buffs.add(new HpRegen(7));

    }
}

