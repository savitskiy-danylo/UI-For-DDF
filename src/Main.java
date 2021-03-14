import Controllers.MBox.MessageBox;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {
    public static void main(String[] args){

    }

    public static void newMessage(){
        System.out.println(MessageBox.getInstance().getMessage().getText());
    }
}
/*
        MessageBox.getInstance().addNewMessageEventListener(() -> newMessage());
        Entity oleg = new Player();
        oleg.setName("Олех");
        Entity serGey = new Player();
        serGey.setName("СерГецій");
        Lash lash = new Lash(oleg);
        Equipment weapon = new Butterfly(serGey);
        LatexCostume costume = new LatexCostume(serGey);
        oleg.getInventory().addItemLoot(lash);
        oleg.getInventory().setWeapon(lash);
        serGey.getInventory().addItemLoot(costume);
        serGey.getInventory().addItemLoot(weapon);
        serGey.getInventory().setArmor(costume);
        serGey.getInventory().setWeapon(weapon);
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