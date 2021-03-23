package UI.Scenes;

import UI.Scenes.Base.Scene;

import java.util.HashMap;
import java.util.Map;

public class SceneContainer {
    private static final Map<String, Scene> scenes = new HashMap<>();
    private static boolean isInit = false;

    private static void init(){

        scenes.put("Prologue 1", new Prologue("You woke up in the lowest floor of the dungeon,\n" +
                " someone stole your sword and outfit.\n" +
                "All you remember is your name ♂Mr.Billy♂\n" +
                " and your brother ♂Van♂ who killed your clan \n" +
                "and and finally you remember the purpose of your life\n" +
                " is revenge for the murder of the clan."));
        scenes.put("Shop", new Shop());
        scenes.put("TheEnd", new TheEnd());
        scenes.put("BuyMenu", new BuyMenu());
        scenes.put("SellMenu", new SellMenu());
        scenes.put("Inventory", new Inventory());
        scenes.put("Fight", new Fight());
        scenes.put("Settings", new Settings());
        scenes.put("MainMenu", new MainMenu());
        scenes.put("FloorMenu", new FloorMenu());
        isInit = true;
    }

    public static Scene getScene(String name){
        if(!isInit) init();
        return scenes.get(name);
    }

    public static Scene[] getScenes(){
        if(!isInit) init();
        return scenes.values().toArray(new Scene[scenes.size()]);
    }
}
