package GachiCore.Builders.Base;

import Controllers.MBox.MessageType;
import GachiCore.Components.Messenger;

public class AiBase {
    protected Messenger messenger;

    protected void setName(String name){
        messenger = new Messenger(name + " was damaged on: ", MessageType.POSITIVE,
                name + " was healed on: ", MessageType.NEGATIVE,
                name + " dodged the attack", MessageType.NEGATIVE,
                name + " died", MessageType.POSITIVE);
    }
}
