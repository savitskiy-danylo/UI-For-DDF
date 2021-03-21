package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Slave;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Buffs.HealPotionT1;
import GachiCore.Components.Buffs.SlaveRageBuff;
import GachiCore.Components.Items.Consumables.Base.SmallHealPotion;
import GachiCore.Components.Items.Equipment.LeatherArmor;
import GachiCore.Components.Items.Equipment.Stick;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class SlaveBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(8, 10, 5,
                11, 3, 80, 2);
        Inventory inventory = new Inventory();
        Slave slave = new Slave(stats, inventory, false);
        Stick stick = new Stick();
        SmallHealPotion potion = new SmallHealPotion();
        inventory.addItem(potion);
        inventory.takeOn(stick);
        inventory.addMoney(randmoney());
        slave.setName("Slave");
        setName("Slave");
        slave.setMessenger(messenger);
        slave.setEnemy(enemy);
        return slave;
    }
    private int randmoney(){
        int lowest = 10;
        int highest = 20;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
