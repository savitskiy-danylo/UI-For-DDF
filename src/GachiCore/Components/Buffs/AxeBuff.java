package GachiCore.Components.Buffs;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import GachiCore.Components.Buffs.Base.Buff;

public class AxeBuff extends Buff {
    public AxeBuff(){refreshOnce=true;}
    private int damage = 20;

    @Override
    protected void takeOnOnce() {
        target.getStats().addDamage(damage);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusDamage(damage);
    }
}

