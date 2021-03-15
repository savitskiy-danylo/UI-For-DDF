package GachiCore.Entities;

import GachiCore.Components.GachiPower;
import GachiCore.Components.Inventory;
import GachiCore.Components.Stats;

public class AlekBuilder implements EntityBuilder{

    @Override
    public Alek build() {

        Stats stats = new Stats(10, 15, 4, 100,
                2, 80, 1);
        Inventory inventory = new Inventory();
        GachiPower gachiPower = new GachiPower(1000, 0,
                2, 1);
        Alek alek = new Alek(stats, inventory, gachiPower);
        return alek;
    }
}
