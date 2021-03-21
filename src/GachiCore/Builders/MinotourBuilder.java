package GachiCore.Builders;

import GachiCore.AI.AIUser;
import GachiCore.AI.Minotour;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.AiBase;
import GachiCore.Components.Items.Consumables.Base.MediumHealPotion;
import GachiCore.Components.Items.Equipment.Axe;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Entity;

import java.util.Random;

public class MinotourBuilder extends AiBase implements AIBuilder {
    @Override
    public AIUser build(Entity enemy) {
        Stats stats = new Stats(20, 23, 4, 40, 2, 80, 2);
        Inventory inventory = new Inventory();
        Minotour minotour = new Minotour(stats, inventory, false);
        Axe axe = new Axe();
        MediumHealPotion potion = new MediumHealPotion();
        inventory.addItem(potion);
        inventory.takeOn(axe);
        inventory.addMoney(randmoney());

        setName("Minotour");
        minotour.setName("Minotour");
        minotour.setMessenger(messenger);
        return minotour;
    }
    private int randmoney(){
        int lowest = 20;
        int highest = 40;
        Random random = new Random();
        int money = random.nextInt(highest - lowest) + lowest;
        return money;
    }
}
