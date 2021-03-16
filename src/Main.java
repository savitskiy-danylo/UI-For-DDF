import Controllers.MBox.MessageBox;

import GachiCore.Components.Items.Consumables.BottleOfSemen;
import GachiCore.Components.Items.Consumables.Grease;
import GachiCore.Components.Items.Equipment.BrokenSword;
import GachiCore.Components.Items.Equipment.LatexCostume;
import GachiCore.Entities.Alek;
import GachiCore.Entities.AlekBuilder;
import GachiCore.Entities.Base.Entity;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Entity> aleks = new ArrayList<>();
    private static int index = 1;
    public static void main(String[] args){
        MessageBox.getInstance().addNewMessageEventListener(() -> newMessage());
        AlekBuilder entityBuilder = new AlekBuilder();

        Alek alekTinker = entityBuilder.build("Alek Tinker");
        aleks.add(alekTinker);
        alekTinker.addActionAfterDeath((Entity entity) -> removeAlek(entity));

        LatexCostume latexCostume = new LatexCostume();
        alekTinker.getInventory().addItem(latexCostume);
        alekTinker.getInventory().takeOn(latexCostume);

        Alek alekEarthSpirit = entityBuilder.build("Alek Earth Spirit");
        aleks.add(alekEarthSpirit);
        alekEarthSpirit.addActionAfterDeath((Entity entity) -> removeAlek(entity));

        BrokenSword brokenSword = new BrokenSword();
        BottleOfSemen bottleOfSemen = new BottleOfSemen();
        Grease grease = new Grease();
        alekEarthSpirit.getInventory().addItem(brokenSword);
        alekEarthSpirit.getInventory().addItem(bottleOfSemen);
        alekEarthSpirit.getInventory().takeOn(brokenSword);
        alekEarthSpirit.getInventory().addItem(grease);


        alekTinker.setEnemy(alekEarthSpirit);
        alekEarthSpirit.setEnemy(alekTinker);
        alekEarthSpirit.getInventory().use(grease);

        while (aleks.size() > 1){
            if(alekEarthSpirit.getStats().getStrength() < 30 && alekEarthSpirit.getInventory().have(bottleOfSemen))
                alekEarthSpirit.getInventory().use(bottleOfSemen);
            alekEarthSpirit.attack();
            alekTinker.attack();

            alekEarthSpirit.nextTurn();
            alekTinker.nextTurn();

            alekEarthSpirit.newTurn();
            alekTinker.newTurn();
        }

    }

    public static void removeAlek(Entity alek){
        for (var anotherAlek :
                aleks) {
            if (anotherAlek.getEnemy() == alek) {
                anotherAlek.setEnemy(null);
            }
        }
        alek.setEnemy(null);
        aleks.remove(alek);
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