package GachiCore.GameHandlers.Floors.Base;

import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.ItemDictionary;
import GachiCore.Entities.Base.Entity;
import GachiCore.GameHandlers.Floor;
import GachiCore.GameHandlers.GachiHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public interface FloorBuilder {
    public default Entity getEnemy(){
        return GachiHandler.getInstance().getHero();
    }
    public Floor getFloor();
    public default AIBuilder getRandomBuilder(Random random, AIBuilder... builders){
        return builders[random.nextInt(builders.length)];
    }

    public default Shop getRandomShop(Random random){
        ArrayList<Item> equipment = new ArrayList<>();
        Arrays.stream(ItemDictionary.getInstance().getItems())
                .filter((Item item) -> item instanceof Equipment)
                .forEach((Item item) -> equipment.add(item));

        int size = equipment.size();
        return new Shop(equipment.get(random.nextInt(size)),
                equipment.get(random.nextInt(size)),
                equipment.get(random.nextInt(size)));

    }
}
