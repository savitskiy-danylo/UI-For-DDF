package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Heterosexual;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class HeterosexualBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(5, 7, 4,
                30, 2, 80, 2);
        Inventory inventory = new Inventory();
        Heterosexual heterosexual = new Heterosexual(stats, inventory, false, 2);
        setName("Simple heterosexual");
        heterosexual.setName("Simple heterosexual");
        heterosexual.setMessenger(messenger);
        heterosexual.setEnemy(enemy);
        return heterosexual;
    }
}
