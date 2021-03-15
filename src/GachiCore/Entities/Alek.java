package GachiCore.Entities;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.GachiPower;
import GachiCore.Components.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.GachiPowerUser;

public class Alek extends GachiPowerUser {
    private MessageBox messageBox = MessageBox.getInstance();
    private Message takeDamage = new Message("I'm fucking cumming ", MessageType.NEGATIVE);
    private Message died = new Message("Slap", MessageType.NEGATIVE);
    private Message dodge = new Message("Successful dodge", MessageType.POSITIVE);

    public Alek(Stats stats, Inventory inventory, GachiPower gachiPower) {
        super(stats, inventory, gachiPower);
        setName("Alek");
    }

    @Override
    public void takeDamage(int damage) {
        int previousStrength = stats.getStrength();
        super.takeDamage(damage);
        if(previousStrength == stats.getStrength()){
            messageBox.addNewMessage(dodge);
        }else if(isAlive()){
            messageBox.addNewMessage(addInformation(takeDamage, previousStrength - stats.getStrength()));
        }
    }

    @Override
    protected void death() {
        super.death();
        messageBox.addNewMessage(died);
    }

    private Message addInformation(Message message, String text){
        return new Message(message.getText() + text, message.getMessageType());
    }

    private Message addInformation(Message message, int text){
        return addInformation(message, Integer.toString(text));
    }
}