package Core.Components;

import Controllers.MBox.Message;
import Controllers.MBox.MessageType;
import Core.Components.Base.Usable.Equipment;
import Core.EntitiesOLD.Base.Entity;

public class Butterfly extends Equipment {
    int damage = 3, agility = 10;
    public Butterfly(Entity target) {
        super(target);
        buff = new BuffForButterfly(target, damage, agility);
        name = "BUTTerfly";
        itemFound = new Message("You found " + name, MessageType.POSITIVE);
        itemBought = new Message("You bought " + name, MessageType.POSITIVE);
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
