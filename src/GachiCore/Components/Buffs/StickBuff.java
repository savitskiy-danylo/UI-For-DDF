package GachiCore.Components.Buffs;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Entities.Base.Entity;

public class StickBuff extends Buff {
    public StickBuff(){
        refreshOnce=true;
    }
    private int damage = 7;
    private MessageBox messageBox = MessageBox.getInstance();
    private Message message;

    @Override
    protected void takeOnOnce() {
        target.getStats().addDamage(damage);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusDamage(damage);
    }
}

