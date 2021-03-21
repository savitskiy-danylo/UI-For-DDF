package GachiCore.Builders;

import Controllers.MBox.MessageType;
import GachiCore.Builders.Base.PlayerBuilder;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Messenger;
import GachiCore.Components.Skills.Base.GachiPower;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.Entities.Billy;

public class BillyBuilder implements PlayerBuilder {
    protected Messenger messenger;

    protected void setName(String name){
        messenger = new Messenger(name + " was damaged on: ", MessageType.NEGATIVE,
                name + " was healed on: ", MessageType.POSITIVE,
                name + " dodged the attack", MessageType.POSITIVE,
                name + " died", MessageType.NEGATIVE);
    }

    @Override
    public GachiPowerUser build() {
        Stats stats = new Stats(10, 15, 4,
                100, 1, 80, 1);
        Inventory inventory = new Inventory();
        GachiPower gachiPower = new GachiPower(5000, 100, 2, 2);
        Billy billy = new Billy(stats, inventory, gachiPower);
        setName("Mr. Billy");
        billy.setName("Mr. Billy");
        billy.setMessenger(messenger);
        return billy;
    }
}
