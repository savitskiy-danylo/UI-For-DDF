package GachiCore.Components.Skills;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Skills.Base.Skill;

public class TurnAround extends Skill {
    private MessageBox messageBox = MessageBox.getInstance();
    private Message message;
    public TurnAround() {
        super("Turn around", "Stick my finger in your ass", 20);
        message = new Message(getDescription(), MessageType.POSITIVE);
    }

    @Override
    public void use() {
        if(canUse()){
            user.getEnemy().getStats().minusStrength(user.getEnemy().getStats().getStrength()/2);
            user.getGachiPower().minusGachiPower(getPrice());
            messageBox.addNewMessage(message);
        }

    }
}
