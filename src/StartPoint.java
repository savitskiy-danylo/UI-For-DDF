

import Main.MainLoop;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class StartPoint {
    public static void main(String[] args){
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen());
        MainLoop mainLoop = MainLoop.getInstance();
        mainLoop.start();
        AnsiConsole.systemUninstall();
    }
}