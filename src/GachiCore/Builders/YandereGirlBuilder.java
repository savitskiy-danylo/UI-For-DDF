package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.YandereGirl;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

public class YandereGirlBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(27, 30, 4, 50, 2, 80, 2);
        Inventory inventory = new Inventory();
        YandereGirl yandereGirl = new YandereGirl(stats, inventory, false);
        setName("YandereGirl");
        yandereGirl.setName("YandereGirl");
        yandereGirl.setMessenger(messenger);
        return yandereGirl;

    }
}
