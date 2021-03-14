package Core.Components;

import Controllers.MBox.Message;
import Controllers.MBox.MessageType;
import Core.Components.Base.Usable.Equipment;
import Core.EntitiesOLD.Base.Entity;

public class LatexCostume extends Equipment {


    public LatexCostume(Entity target) {
        super(target);
        name = "LatexWish";
        itemFound = new Message("You found " + name, MessageType.POSITIVE);
        itemBought = new Message("You bought " + name, MessageType.POSITIVE);
        buff = new PlusArmor(target, 5);

    }

    @Override
    public void takeOff() {
        target.removeBuff(buff);

    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        target.addBuff(buff);


    }
}
