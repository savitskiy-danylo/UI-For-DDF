package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Slime;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class SlimeBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(5, 7, 2,
                10, 2, 80, 2);
        Inventory inventory = new Inventory();
        Slime slime = new Slime(stats, inventory, false);
        setName("Slime");
        slime.setName("Slime");
        slime.setMessenger(messenger);
        slime.setEnemy(enemy);
        return slime;
    }
}
