import Controllers.MBox.MessageBox;

import GachiCore.AI.AIUser;
import GachiCore.Builders.Base.AIBuilder;
import GachiCore.Builders.Base.PlayerBuilder;
import GachiCore.Builders.HeterosexualBuilder;
import GachiCore.Builders.PoidaBuilder;
import GachiCore.Components.Items.Equipment.BrokenSword;
import GachiCore.Entities.Base.Entity;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.FloorEnemies;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Entity> aleks = new ArrayList<>();
    private static int index = 1;
    public static void main(String[] args){
        MessageBox.getInstance().addNewMessageEventListener(() -> newMessage());
        PlayerBuilder player = new PoidaBuilder();
        GachiPowerUser gpUser = player.build();
        gpUser.addActionAfterDeath(Main::end);
        BrokenSword brokenSword = new BrokenSword();
        gpUser.getInventory().addItem(brokenSword);
        //gpUser.getInventory().takeOn(brokenSword);

        AIBuilder ai = new HeterosexualBuilder();

        ArrayList<AIUser> aiUsers = new ArrayList<>();
        for (int index = 0; index < 5; index++){
            aiUsers.add(ai.build(gpUser));
        }
        FloorEnemies firstFloor = new FloorEnemies(gpUser, aiUsers.toArray(new AIUser[aiUsers.size()]));
        firstFloor.addActionAfterDefeat(Main::end);
        while (gpUser.isAlive() && !firstFloor.isClear()){
            gpUser.attack();
            gpUser.attack();
            gpUser.attack();
            gpUser.attack();
            gpUser.nextTurn();

            firstFloor.turn();
            firstFloor.nextTurn();

            gpUser.newTurn();
            firstFloor.newTurn();
        }
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