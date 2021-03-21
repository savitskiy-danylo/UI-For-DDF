package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.SWATMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class SWATManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(25, 27, 4, 60, 2, 80, 2);
        Inventory inventory = new Inventory();
        SWATMan swatMan = new SWATMan(stats, inventory, true);
        setName("SWATMan");
        swatMan.setName("SWATMan");
        swatMan.setMessenger(messenger);
        return swatMan;
    }
}
