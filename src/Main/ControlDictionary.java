package Main;

import GachiCore.GameHandlers.SaveHandler;
import UI.Actions;

import java.util.HashMap;
import java.util.Map;

public class ControlDictionary {
    private static final Map<String, Actions> mainDictionary = new HashMap<>();
    private static final Map<String, Runnable> additionalDictionary = new HashMap<>();

    private ControlDictionary() { }

    static {
        mainDictionary.put("w", Actions.UP);
        mainDictionary.put("s", Actions.DOWN);
        mainDictionary.put("e", Actions.OK);

        additionalDictionary.put("exit", () -> StartPoint.onExit());
        additionalDictionary.put("save", () -> save());
    }

    private static void save(){
        SaveHandler.getInstance().save();
        MainLoop.getInstance().redraw();
    }

    public static boolean isControl(String control){ return mainDictionary.containsKey(control); }
    public static boolean isCommand(String command){ return additionalDictionary.containsKey(command); }

    public static Actions getAction(String control){ return mainDictionary.get(control); }
    public static void executeCommand(String command){ additionalDictionary.get(command).run(); }
}
