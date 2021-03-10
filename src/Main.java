import UI.Components.Borders;
import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.Builders.Realizations.BiosView;
import UI.Components.Builders.Realizations.SimpleView;
import UI.Components.View;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {
    public static void main(String[] args){
        AnsiConsole.systemInstall();
        System.out.print(ansi().eraseScreen());
        ViewBuilder viewBuilder = new BiosView();
        View view = viewBuilder.build(" .-.\n" +
                " | |\n" +
                ".'s`.\n" +
                "`._.'");
        view.draw();
        new Scanner(System.in).nextLine();
        AnsiConsole.systemUninstall();
    }
}
/*

* */