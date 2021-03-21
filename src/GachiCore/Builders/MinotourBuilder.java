package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Minotour;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class MinotourBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(20, 23, 4, 40, 2, 80, 2);
        Inventory inventory = new Inventory();
        Minotour minotour = new Minotour(stats, inventory, false);
        setName("Minotour");
        minotour.setName("Minotour");
        minotour.setMessenger(messenger);
        return minotour;
    }
}
