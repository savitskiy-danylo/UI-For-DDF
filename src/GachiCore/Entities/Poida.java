package GachiCore.Entities;

import GachiCore.Components.Skills.Base.GachiPower;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.GachiPowerUser;

public class Poida extends GachiPowerUser {

    public Poida(Stats stats, Inventory inventory, GachiPower gachiPower) {
        super(stats, inventory, gachiPower);
    }
}