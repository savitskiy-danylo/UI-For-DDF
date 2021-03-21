package GachiCore.Components.Skills;

import GachiCore.Components.Items.Base.Item;

import java.util.HashMap;
import java.util.Map;

public class SkillDictionary {
    private static final SkillDictionary instance = new SkillDictionary();
    private SkillDictionary(){}

    public static SkillDictionary getInstance() {
        return instance;
    }

    private Map<String, Item> skills = new HashMap<>();

    {
        /*
           skills.put("NAME_OF_ITEM, new Item());
         */
    }

    public Item getSkill(String name){
        return skills.get(name);
    }
}
