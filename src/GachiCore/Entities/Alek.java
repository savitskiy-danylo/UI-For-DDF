package GachiCore.Entities;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Skills.Base.GachiPower;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.GachiPowerUser;

public class Alek extends GachiPowerUser {
    private MessageBox messageBox = MessageBox.getInstance();
    private Message takeDamage = new Message("I'm fucking cumming ", MessageType.NEGATIVE);
    private Message died = new Message("Slap", MessageType.NEGATIVE);
    private Message dodge = new Message("Successful dodge", MessageType.POSITIVE);
    private Message heal = new Message(" healed on ", MessageType.POSITIVE);

    public Alek(Stats stats, Inventory inventory, GachiPower gachiPower) {
        super(stats, inventory, gachiPower);
    }

    @Override
    public void heal(int number) {
        super.heal(number);
        messageBox.addNewMessage(addInformation(heal, number));
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        takeDamage = new Message(name + ". " + takeDamage.getText(), takeDamage.getMessageType());
        died = new Message(name + ". " + died.getText(), died.getMessageType());
        dodge = new Message(name + ". " + dodge.getText(), dodge.getMessageType());
        heal = new Message(name + heal.getText(), heal.getMessageType());

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