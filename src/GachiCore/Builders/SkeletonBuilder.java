package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Skeleton;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.SmallHealPotion;
import GachiCore.Components.Items.Equipment.Bow;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class SkeletonBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(10, 12, 2,
                11, 2, 80, 2);
        Inventory inventory = new Inventory();
        Skeleton skeleton = new Skeleton(stats, inventory,true);
        Bow bow = new Bow();
        SmallHealPotion potion = new SmallHealPotion();
        inventory.addItem(potion);
        setName("Skeleton");
        skeleton.setName("Skeleton ");
        bow.setOwner(skeleton);
        inventory.takeOn(bow);
        inventory.addMoney(randmoney());
        skeleton.setMessenger(messenger);
        skeleton.setEnemy(enemy);
        return skeleton;

    }
    private int randmoney(){
        int lowest = 10;
        int highest = 20;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
