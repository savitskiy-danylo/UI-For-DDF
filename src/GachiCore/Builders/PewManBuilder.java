package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.PewMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class PewManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(16, 20, 2, 22, 2, 80, 2);
        Inventory inventory = new Inventory();
        PewMan pewMan = new PewMan(stats, inventory, true);
        setName("PewMan");
        pewMan.setName("PewMan");
        pewMan.setMessenger(messenger);
        return pewMan;
    }
}
