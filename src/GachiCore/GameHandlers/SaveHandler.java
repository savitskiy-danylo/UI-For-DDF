package GachiCore.GameHandlers;

import GachiCore.Builders.Base.PlayerBuilder;
import GachiCore.Builders.BillyBuilder;
import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.ItemDictionary;
import GachiCore.Entities.Base.GachiPowerUser;
import Main.MainLoop;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveHandler {
    private static final SaveHandler instance = new SaveHandler();
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private GachiPowerUser user = gachiHandler.getHero();
    private final FloorHandler floorHandler = FloorHandler.getInstance();
    private final PlayerBuilder playerBuilder = new BillyBuilder();
    private final ItemDictionary itemDictionary = ItemDictionary.getInstance();
    private final File fileSave = new File("Save.txt");

    private SaveHandler(){ }

    public static SaveHandler getInstance() {
        return instance;
    }

    public void save(){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileSave, false), "Cp1251"))){
            if(!fileSave.exists()) fileSave.createNewFile();
            writer.write(getSave());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        String fileContext = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileSave), "Cp1251"))){
            while (reader.ready()){
                fileContext += reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseSave(fileContext);
    }

    private void parseSave(String context){
        if(context.isBlank() || context.isEmpty()) {
            startNewGame();
            return;
        }
        GachiPowerUser user = playerBuilder.build();
        String[] fields = context.split(";");
        String[] items = new String[0];
        String armor = "", weapon = "";
        int money = 0, floor = 1, gachiPower = 0;
        for (var field:
             fields) {

            if(field.contains("items"))
            {
                items = field.split("=")[1].split(",");
                continue;
            }

            if(field.contains("money")){
                money = Integer.parseInt(field.split("=")[1]);
                continue;
            }

            if(field.contains("armor")){
                armor = field.split("=")[1];
                continue;
            }

            if(field.contains("weapon")){
                weapon = field.split("=")[1];
                continue;
            }

            if(field.contains("floor")){
                floor = Integer.parseInt(field.split("=")[1]);
                continue;
            }

            if(field.contains("gachiPower")){
                gachiPower = Integer.parseInt(field.split("=")[1]);
                continue;
            }
        }
        user.getInventory().addMoney(money);
        user.getGachiPower().addGachiPower(gachiPower);
        Arrays.stream(items)
                .forEach((String name) -> user.getInventory()
                        .addItem(ItemDictionary
                                .getInstance()
                                .getItem(name)));
        user.getInventory().takeOn((Equipment) itemDictionary.getItem(armor));
        user.getInventory().takeOn((Equipment) itemDictionary.getItem(weapon));
        GameHandler.getInstance().loadGame(user, floor);
    }

    private void startNewGame(){
        GameHandler.getInstance().startNewGame();
    }

    private String getSave(){
        user = gachiHandler.getHero();
        String save = "money=" + user.getInventory().getMoney() + ";\n";
        ArrayList<String> itemNames = new ArrayList<>();
        user.getInventory().getEquipments().forEach((Item item) -> itemNames.add(item.getName()));
        user.getInventory().getConsumables().forEach((Item item) -> itemNames.add(item.getName()));
        save += "items=";
        for (var name :
                itemNames) {
            save += name + ",";
        }
        save += ";\n";

        Equipment armor = user.getInventory().getArmor();
        if(armor==null)
            save += "armor=NONE";
        else
            save += "armor=" + armor.getName();
        save += ";\n";

        Equipment weapon = user.getInventory().getWeapon();
        if(weapon==null)
            save += "weapon=NONE";
        else
            save+= "weapon=" + weapon.getName();
        save += ";\n";

        save += "gachiPower=" + user.getGachiPower().getGachiPowerCurrent() + ";\n";
        save += "floor=" + floorHandler.getNumberOfFloor() + ";\n";
        return save;
    }
}
