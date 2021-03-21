package GachiCore.Components.Buffs;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Components.Buffs.Base.BuffRefreshType;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class BowBuff extends Buff {
    public BowBuff(){refreshOnce=true; refreshEachAttack=true;}
    private final Random random = new Random();
    private int damage = 5;
    private int misschance = 11;
    private MessageBox messageBox = MessageBox.getInstance();
    private Message message;

    @Override
    public void setTarget(Entity target) {
        super.setTarget(target);
        message = new Message(target.getName() + "Missed shot!", MessageType.NEGATIVE);

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
        if(random.nextInt(100) + 1 < misschance){
            target.getStats().minusCurrentDamage(target.getStats().getDamageCurrent());
            messageBox.addNewMessage(message);

        }



    }


}
