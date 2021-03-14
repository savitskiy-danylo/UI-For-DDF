package Core.Components;

import Controllers.MBox.Message;
import Controllers.MBox.MessageType;
import Core.Components.Base.Usable.Equipment;
import Core.EntitiesOLD.Base.Entity;

public class Lash extends Equipment {
    public Lash(Entity target) {
        super(target);
        name = "Lash";
        itemFound = new Message("You found " + name, MessageType.POSITIVE);
        itemBought = new Message("You bought " + name, MessageType.POSITIVE);
        buff = new PlusDamage(target, 5);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        target.addBuff(buff);
    }

    @Override
    public void takeOff() {
        target.removeBuff(buff);
    }
}
