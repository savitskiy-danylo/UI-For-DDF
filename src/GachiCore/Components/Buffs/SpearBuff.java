package GachiCore.Components.Buffs;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import GachiCore.Components.Buffs.Base.Buff;

public class SpearBuff extends Buff {
    public SpearBuff(){refreshOnce=true;}
    private int damage = 18;
    private MessageBox messageBox = MessageBox.getInstance();
    private Message message;

    @Override
    protected void takeOnOnce() {
        target.getStats().addDamage(damage);
        target.getStats().addAgility(10);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusDamage(damage);
        target.getStats().minusAgility(10);
    }



}
