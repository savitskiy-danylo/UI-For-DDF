package GachiCore.Components.Items.Consumables;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Buffs.GreaseConsumable;
import GachiCore.Components.Items.Consumables.Base.Consumable;

public class Grease extends Consumable {
    MessageBox messageBox = MessageBox.getInstance();
    Message message = new Message("Smazan horosho", MessageType.POSITIVE);

    public Grease() {
        super("Greese for mood", "Really sticky", 16, 8);
        buffs.add(new GreaseConsumable());

    }

    @Override
    public void use() {
        super.use();
        messageBox.addNewMessage(message);
    }
}
