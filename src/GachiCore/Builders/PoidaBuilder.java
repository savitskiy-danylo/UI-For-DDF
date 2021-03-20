package GachiCore.Builders;

import Controllers.MBox.MessageType;
import GachiCore.Builders.Base.PlayerBuilder;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Messenger;
import GachiCore.Components.Skills.Base.GachiPower;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.Entities.Poida;

public class PoidaBuilder implements PlayerBuilder {
    protected Messenger messenger;

    protected void setName(String name){
        messenger = new Messenger(name + " was damaged on: ", MessageType.POSITIVE,
                name + " was healed on: ", MessageType.NEGATIVE,
                name + " dodged the attack", MessageType.NEGATIVE,
                name + " died", MessageType.POSITIVE);
    }

    @Override
    public GachiPowerUser build() {
        Stats stats = new Stats(10, 15, 4,
                100, 1, 80, 1);
        Inventory inventory = new Inventory();
        GachiPower gachiPower = new GachiPower(5000, 100, 2, 2);
        Poida poida = new Poida(stats, inventory, gachiPower);
        setName("Mr. Dickensos");
        poida.setName("Mr. Dickensos");
        poida.setMessenger(messenger);
        return poida;
    }
}
