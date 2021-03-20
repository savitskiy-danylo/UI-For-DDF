package GachiCore.Components.Buffs;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class SlaveRageBuff extends Buff {
    public SlaveRageBuff(){refreshEachTurn=true;}
    private int hpLimit = 20;
    private int num;
    private final Random random = new Random();
    private MessageBox messageBox = MessageBox.getInstance();
    private Message message;

    private boolean checkhp(){
        return target.getStats().getStrength() < hpLimit;
    }
    @Override
    public void setTarget(Entity target) {
        super.setTarget(target);
        message = new Message(target.getName() + "In Rage", MessageType.NEGATIVE);

    }

    @Override
    protected void takeOnEachTurn() {
        if (checkhp()){
            int min = 1;
            int max = 7;
            num = random.nextInt(max - min + 1) + min;
            target.getStats().addArmor(num);
            messageBox.addNewMessage(message);
        }
    }

    @Override
    protected void takeOffEachTurn() {
        target.getStats().minusArmor(num);
        num = 0;

    }
}
