package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Slave;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Buffs.SlaveRageBuff;
import GachiCore.Components.Items.Equipment.LeatherArmor;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class SlaveBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(8, 10, 5,
                11, 3, 80, 2);
        Inventory inventory = new Inventory();
        Slave slave = new Slave(stats, inventory, false);
        slave.setName("Slave");
        setName("Slave");
        slave.setMessenger(messenger);
        slave.setEnemy(enemy);
        return slave;
    }
}
