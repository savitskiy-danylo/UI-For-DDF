package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.M4A4Buff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;
import GachiCore.Entities.Base.Enemy;

public class M4A4 extends Equipment {
    public M4A4(){
        super("M4A4", "Dangerous weapon!\ndamage + 32; misschance + 8%", 260, 130, EquipmentType.WEAPON);
        setQuality(Quality.THREE);
        buffs.add(new M4A4Buff());

    }
    @Override
    public void takeOn() {
        super.takeOn();
        if (owner instanceof Enemy) {
            ((Enemy) owner).setRange(true);
        }

    }

    @Override
    public void takeOff() {
        super.takeOff();
        if (owner instanceof Enemy) {
            ((Enemy) owner).setRange(false);
        }
    }
}
