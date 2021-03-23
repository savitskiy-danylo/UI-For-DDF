package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.WaterPistolBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;
import GachiCore.Entities.Base.Enemy;

public class WaterPistol extends Equipment {
    public WaterPistol(){
        super("WaterPistol", "Shoots water, yaay!\ndamage + 27; misschance 10%", 100, 50, EquipmentType.WEAPON);
        setQuality(Quality.TWO);
        buffs.add(new WaterPistolBuff());
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
