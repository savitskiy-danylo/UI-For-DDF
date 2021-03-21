package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.YandereGirl;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Equipment.BloodyKnife;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class YandereGirlBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(27, 30, 4, 50, 2, 80, 2);
        Inventory inventory = new Inventory();
        YandereGirl yandereGirl = new YandereGirl(stats, inventory, false);
        BloodyKnife bloodyKnife = new BloodyKnife();
        inventory.takeOn(bloodyKnife);
        inventory.addMoney(randmoney());
        setName("YandereGirl");
        yandereGirl.setName("YandereGirl");
        yandereGirl.setMessenger(messenger);
        return yandereGirl;
    }
    private int randmoney(){
        int lowest = 40;
        int highest = 80;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
