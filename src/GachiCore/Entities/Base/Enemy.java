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

    public void setRange(boolean range) {
        isRange = range;
    }

    @Override
    public void attack() {
        if(!canAttack()) return;
        super.attack();
    }

    public boolean canAttack(){
        return isVanguard || isRange;
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
