package GachiCore.Builders.Base;

import GachiCore.AI.AIUser;
import GachiCore.AI.Slave;
import GachiCore.Components.Buffs.SlaveRageBuff;
import GachiCore.Components.Items.Equipment.LeatherArmor;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class SlaveBuilder extends AiBase implements AIBuilder{
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(10, 14, 5,
                30, 3, 80, 2);
        Inventory inventory = new Inventory();
        Slave slave = new Slave(stats, inventory, false);
        LeatherArmor armor = new LeatherArmor();
        slave.setName("Slave ");
        setName("Slave");
        slave.setMessenger(messenger);
        slave.addBuff(new SlaveRageBuff());
        slave.setEnemy(enemy);
        return slave;
    }
}
