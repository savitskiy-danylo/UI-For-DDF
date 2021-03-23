package GachiCore.Builders.Base;

import Controllers.MBox.MessageType;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Messenger;

import java.util.Random;

public class AiBase {
    protected Messenger messenger;

    protected void setName(String name){
        messenger = new Messenger(name + " was damaged on: ", MessageType.POSITIVE,
                name + " was healed on: ", MessageType.NEGATIVE,
                name + " dodged the attack", MessageType.NEGATIVE,
                name + " died", MessageType.POSITIVE);
    }
    protected Consumable getPotion(int chance, Consumable consumable){
        Random random = new Random();
        if(random.nextInt(100) + 1 < chance){
            return consumable;
        }
        else{
            return null;
        }

    }
}
