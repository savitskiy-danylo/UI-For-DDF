package GachiCore.Components.Items;

import GachiCore.Components.Items.Base.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemDictionary {
    private static final ItemDictionary instance = new ItemDictionary();
    private ItemDictionary(){}

    public static ItemDictionary getInstance() {
        return instance;
    }

    private Map<String, Item> items = new HashMap<>();

    {
        /*
           items.put("NAME_OF_ITEM, new Item());
         */
    }

    public Item getItem(String name){
        return items.get(name);
    }

}
