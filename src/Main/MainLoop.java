package Main;

import UI.Scenes.Base.Scene;
import UI.Scenes.SceneContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainLoop {
    private static final MainLoop mainLoop = new MainLoop();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Scene currentScene = null;

    private final Scanner scanner = new Scanner(System.in);

    private MainLoop() {
    }

    public static MainLoop getInstance() {
        return mainLoop;
    }

    private void addScenes(Scene... newScenes) {
        Arrays.stream(newScenes).forEach((Scene scene) -> scenes.add(scene));
    }

    public void start() {
        if(scenes.isEmpty()) addScenes(SceneContainer.getScenes());
        String line;
        if(currentScene == null) searchCurrentScene();
        while (currentScene.isCurrentScene()) {
            line = scanner.nextLine();
            if (ControlDictionary.isControl(line))
                currentScene.action(ControlDictionary.getAction(line));
            if (ControlDictionary.isCommand(line))
                ControlDictionary.executeCommand(line);
        }
        searchCurrentScene();
    }

    private void searchCurrentScene() {
        currentScene = null;
        for (var scene :
                scenes) {
            if (scene.isCurrentScene()) {
                currentScene = scene;
                break;
            }
        }
        if(currentScene != null) start();
        else System.exit(0);//TODO Точка выхода
    }
}

