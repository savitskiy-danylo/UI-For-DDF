package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.GymMan;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.LargeHealPotion;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class GymManBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(25, 27, 4, 80, 2, 3, 2);
        Inventory inventory = new Inventory();
        GymMan gymMan = new GymMan(stats ,inventory, false);
        LargeHealPotion potion = new LargeHealPotion();
        inventory.addItem(potion);
        inventory.addMoney(randmoney());
        setName("GymMan");
        gymMan.setName("GymMan");
        gymMan.setMessenger(messenger);
        return gymMan;
    }
    private int randmoney(){
        int lowest = 40;
        int highest = 80;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
