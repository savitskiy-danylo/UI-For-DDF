package UI.Scenes;

import UI.Scenes.Base.Scene;

import java.util.HashMap;
import java.util.Map;

public class SceneContainer {
    private static final Map<String, Scene> scenes = new HashMap<>();
    private static boolean isInit = false;

    private static void init(){
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
