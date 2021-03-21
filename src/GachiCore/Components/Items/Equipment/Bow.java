package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.BowBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;
import GachiCore.Entities.Base.Enemy;

public class Bow extends Equipment {
    public Bow() {
        super("Bow", "ranged thing\ndamage + 5; misschance: 11%", 40, 20, EquipmentType.WEAPON);
        setQuality(Quality.ONE);
        buffs.add(new BowBuff());
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
