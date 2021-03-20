
import UI.Controls.Base.Container;
import UI.Controls.HorizontalStackPanel;
import UI.Controls.Label;
import UI.Controls.VerticalStackPanel;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        AnsiConsole.systemInstall();
        Container floor = new VerticalStackPanel();

        Container mainPart = new VerticalStackPanel();
        Label inventory = new Label();
        inventory.setText("Inventory");
        Label shop = new Label();
        shop.setText("Shop");
        mainPart.addControl(inventory);
        mainPart.addControl(shop);

        Container level = new HorizontalStackPanel();
        Label left = new Label();
        left.setText("Left");
        Label right = new Label();
        right.setText("Right");
        level.addControl(left);
        level.addControl(right);

        floor.addControl(mainPart);
        floor.addControl(level);

        floor.draw();

        new Scanner(System.in).nextLine();
        AnsiConsole.systemUninstall();
    }
}