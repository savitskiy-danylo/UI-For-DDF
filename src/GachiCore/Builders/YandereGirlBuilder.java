package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.YandereGirl;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Consumables.LargeHealPotion;
import GachiCore.Components.Items.Consumables.SmallHealPotion;
import GachiCore.Components.Items.Equipment.BloodyKnife;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class YandereGirlBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(15, 19, 4, 50, 2, 80, 2);
        Inventory inventory = new Inventory();
        YandereGirl yandereGirl = new YandereGirl(stats, inventory, false);
        BloodyKnife bloodyKnife = new BloodyKnife();
        Consumable largeHealPotion  = new LargeHealPotion();
        largeHealPotion = getPotion(50, largeHealPotion);
        if (largeHealPotion != null){
            inventory.addItem(largeHealPotion);
        }
        bloodyKnife.setOwner(yandereGirl);
        inventory.takeOn(bloodyKnife);
        inventory.addMoney(randmoney());
        setName("YandereGirl");
        yandereGirl.setName("YandereGirl");
        yandereGirl.setMessenger(messenger);
        yandereGirl.setEnemy(enemy);
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
