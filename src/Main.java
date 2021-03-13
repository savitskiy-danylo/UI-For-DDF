import Controllers.MBox.MessageBox;
import Core.*;
import Core.Components.Lash;
import Core.Player;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {
    public static void main(String[] args){
        MessageBox.getInstance().addNewMessageEventListener(() -> newMessage());
        Entity oleg = new Player();
        Entity serGey = new Player();
        Lash lash = new Lash(oleg);
        oleg.getInventory().addItemLoot(lash);
        oleg.getInventory().setArmor(lash);
        oleg.setEnemy(serGey);
        serGey.setEnemy(oleg);
        while (!oleg.isDead() && !serGey.isDead()) {
            oleg.attack();
            serGey.attack();
            oleg.attack();
            serGey.attack();
            oleg.nextTurn();
            serGey.nextTurn();
        }
    }

    public static void newMessage(){
        System.out.println(MessageBox.getInstance().getMessage().getText());

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