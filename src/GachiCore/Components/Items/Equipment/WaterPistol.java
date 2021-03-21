package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;
import GachiCore.Entities.Base.Enemy;

public class WaterPistol extends Equipment {
    public WaterPistol(){
        super("WaterPistol", "Shoots water, yaay!", 100, 50, EquipmentType.WEAPON);
        setQuality(Quality.TWO);
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
