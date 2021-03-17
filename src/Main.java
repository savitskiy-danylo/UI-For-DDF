import Controllers.MBox.MessageBox;

import GachiCore.AI.AIUser;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.PlayerBuilder;
import GachiCore.Builders.HeterosexualBuilder;
import GachiCore.Builders.PoidaBuilder;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.BrokenSword;
import GachiCore.Entities.Base.Entity;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.FloorEnemies;
import GachiCore.GameHandlers.GachiHandler;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Entity> aleks = new ArrayList<>();
    private static int index = 1;
    public static void main(String[] args){
        MessageBox.getInstance().addNewMessageEventListener(() -> newMessage());
        PlayerBuilder player = new PoidaBuilder();
        GachiPowerUser gpUser = player.build();
        gpUser.addActionAfterDeath(Main::end);

        GachiHandler gachiHandler = GachiHandler.getInstance();
        gachiHandler.setHero(gpUser);
        gachiHandler.leftSide();
        while (!gachiHandler.floorIsClear() && gpUser.isAlive()) {
            gpUser.attack();
            gpUser.attack();
            gpUser.attack();
            gpUser.attack();
            gachiHandler.nextTurn();
        }
        printGpUser(gpUser);

    }

    public static void printGpUser(GachiPowerUser gachiPowerUser){
        System.out.println(gachiPowerUser.getName() + " won!");
        System.out.println("Money: " + gachiPowerUser.getInventory().getMoney());
        gachiPowerUser.getInventory().getEquipments().forEach((Equipment equip) -> System.out.println("Item: " + equip.getName()));
    }

    public static void end(){
        System.exit(0);
    }

    public static void end(Entity entity){
        System.out.println(entity.getName() + " died.");
        System.exit(0);
    }


    public static void newMessage(){
        System.out.println(index + ". " + MessageBox.getInstance().getMessage().getText());
        index++;
    }
}
/*
        MessageBox messageBox = MessageBox.getInstance();
        messageBox.addNewMessageEventListener(() -> newMessage());
        messageBox.addNewMessage(new Message("Hello!", MessageType.SYSTEM));



        AnsiConsole.systemInstall();
        System.out.print(ansi().eraseScreen());
        ViewBuilder viewBuilder = new BiosView();
        View view = viewBuilder.build("Button");
        view.draw();
        new Scanner(System.in).nextLine();
        AnsiConsole.systemUninstall();
* */