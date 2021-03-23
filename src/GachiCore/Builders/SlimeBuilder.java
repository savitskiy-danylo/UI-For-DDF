package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Slime;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Consumables.SmallHealPotion;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class SlimeBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(5, 7, 2,
                10, 2, 80, 2);
        Inventory inventory = new Inventory();
        Slime slime = new Slime(stats, inventory, false);
        Consumable smallHealPotion = new SmallHealPotion();
        smallHealPotion = getPotion(50, smallHealPotion);
        if (smallHealPotion != null){
            inventory.addItem(smallHealPotion);
        }
        inventory.addMoney(randmoney());
        setName("Slime");
        slime.setName("Slime");
        slime.setMessenger(messenger);
        slime.setEnemy(enemy);
        return slime;
    }
    private int randmoney(){
        int lowest = 10;
        int highest = 20;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
