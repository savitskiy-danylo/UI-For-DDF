package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Skeleton;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Equipment.Bow;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class SkeletonBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(6, 10, 2,
                19, 2, 80, 2);
        Inventory inventory = new Inventory();
        Skeleton skeleton = new Skeleton(stats, inventory,true);
        Bow bow = new Bow();
        setName("Skeleton");
        skeleton.setName("Skeleton ");
        inventory.addItem(bow);
        inventory.takeOn(bow);
        skeleton.setMessenger(messenger);
        skeleton.setEnemy(enemy);
        return skeleton;

    }
}
