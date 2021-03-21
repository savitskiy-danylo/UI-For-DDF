package GachiCore.Components.Buffs;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class BloodyKnifeBuff extends Buff {
    private final Random random = new Random();
    private int additionalDamage = 0, procChance = 15;
    private int damage = 17;
    private double criticalDamage = 0.2;
    private MessageBox messageBox = MessageBox.getInstance();
    private Message message;

    public BloodyKnifeBuff() {
        refreshOnce = true;
        refreshEachAttack = true;
    }
    @Override
    public void setTarget(Entity target) {
        super.setTarget(target);
        message = new Message(target.getName() + " deals a critical damage!", MessageType.POSITIVE);
    }
    @Override
    protected void takeOnOnce() {
        target.getStats().addDamage(damage);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusDamage(damage);
    }

    @Override
    protected void takeOnEachAttack() {
        if(random.nextInt(100) + 1 < procChance){
            additionalDamage = (int) (target.getStats().getDamageCurrent() * criticalDamage);
            target.getStats().addCurrentDamage(additionalDamage);
            messageBox.addNewMessage(message);
        }
    }

    @Override
    protected void takeOffEachAttack() {
        additionalDamage = 0;
    }
}
