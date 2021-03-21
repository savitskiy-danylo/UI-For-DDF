package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Slime;
import GachiCore.AI.SpearMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class SpearManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(14, 18, 2,
                30, 2, 80, 2);
        Inventory inventory = new Inventory();
        SpearMan spearMan = new SpearMan(stats, inventory, false);
        setName("SpearMan");
        spearMan.setName("SpearMan");
        spearMan.setMessenger(messenger);
        spearMan.setEnemy(enemy);
        return spearMan;
    }
}
