package GachiCore.Entities.Base;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;

public class Enemy extends Entity{
    protected boolean isRange = false, isVanguard = false;

    public Enemy(Stats stats, Inventory inventory, boolean isRange) {
        super(stats, inventory);
        this.isRange = isRange;
    }

    @Override
    public void attack() {
        if(!isVanguard && !isRange) return;
        super.attack();
    }

    public boolean isRange() {
        return isRange;
    }

    public void positionChanged(){
        stats.minusActionPoints(stats.getPriceOfAttack());
        MessageBox.getInstance().addNewMessage(new Message("Swap", MessageType.SYSTEM));
    }

    public void setVanguard(boolean flag){
        isVanguard = flag;
    }
}
