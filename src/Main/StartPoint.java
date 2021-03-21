package Main;

import GachiCore.GameHandlers.SaveHandler;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class StartPoint {
    private static final SaveHandler saveHandler = SaveHandler.getInstance();

    public static void onStartup(){
        saveHandler.load();
    }

    public static void main(String[] args){
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen());
        onStartup();
        MainLoop mainLoop = MainLoop.getInstance();
        mainLoop.start();
        onExit();
    }

    public static void onExit(){
        saveHandler.save();
        AnsiConsole.systemUninstall();
        System.exit(0);
    }
}