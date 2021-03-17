package GachiCore.Components;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;

public class Messenger {
    private final String takeDamage, heal, dodge, death;
    private MessageType takeDamageType, healType, dodgeType, deathType;

    private final MessageBox messageBox = MessageBox.getInstance();
    public Messenger(String takeDamage, MessageType takeDamageType,
                     String heal, MessageType healType,
                     String dodge, MessageType dodgeType,
                     String death, MessageType deathType) {
        this.takeDamage = takeDamage;
        this.takeDamageType = takeDamageType;
        this.heal = heal;
        this.healType = healType;
        this.dodge = dodge;
        this.dodgeType = dodgeType;
        this.death = death;
        this.deathType = deathType;
    }

    public void setTakeDamageType(MessageType takeDamageType) {
        this.takeDamageType = takeDamageType;
    }

    public void setHealType(MessageType healType) {
        this.healType = healType;
    }

    public void setDodgeType(MessageType dodgeType) {
        this.dodgeType = dodgeType;
    }

    public void setDeathType(MessageType deathType) {
        this.deathType = deathType;
    }


    public void sendTakeDamage(int damage){
        messageBox.addNewMessage(new Message(takeDamage + intToString(damage), takeDamageType));
    }

    public void sendHeal(int heal){
        messageBox.addNewMessage(new Message(this.heal + intToString(heal), healType));
    }

    public void sendDodge(){
        messageBox.addNewMessage(new Message(dodge, dodgeType));
    }

    public void sendDeath(){
        messageBox.addNewMessage(new Message(death, deathType));
    }

    private String intToString(int number){
        return Integer.toString(number);
    }
}
