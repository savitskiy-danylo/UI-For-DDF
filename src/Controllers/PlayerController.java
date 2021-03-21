package Controllers;

import GachiCore.Components.Items.Base.Item;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.FloorHandler;
import GachiCore.GameHandlers.GachiHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerController{
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private final FloorHandler floorHandler = FloorHandler.getInstance();
    private final GachiPowerUser player = gachiHandler.getHero();
    private static final PlayerController instance = new PlayerController();
    private PlayerController(){}

    public static PlayerController getInstance() {
        return instance;
    }

    public int getFloorNumber(){
        return floorHandler.getNumberOfFloor();
    }

    public void attack(){
        player.attack();
    }

    public void nextTurn(){
        player.nextTurn();
    }

    public boolean isClear(){
        return floorHandler.getCurrentFloor().isClear();
    }

    public ItemInformation[] getItems(){
        ArrayList<ItemInformation> itemInformationList = new ArrayList<>();
        Arrays.stream(getEquipment()).peek((ItemInformation itemInfo) -> itemInformationList.add(itemInfo));
        Arrays.stream(getConsumables()).peek((ItemInformation itemInfo) -> itemInformationList.add(itemInfo));
        return itemInformationList.toArray(new ItemInformation[itemInformationList.size()]);
    }

    public ItemInformation[] getEquipment(){
        ArrayList<ItemInformation> itemInformationList = new ArrayList<>();
        player.getInventory().getEquipments().forEach((Equipment equipment) -> itemInformationList.add(
                new ItemInformation(equipment.getName(), equipment.getDescription(), equipment.getPurchasePrice(),
                        equipment.getSellingPrice())));
        return itemInformationList.toArray(new ItemInformation[itemInformationList.size()]);
    }

    public ItemInformation[] getConsumables(){
        ArrayList<ItemInformation> itemInformationList = new ArrayList<>();
        player.getInventory().getConsumables().forEach((Consumable consumables) -> itemInformationList.add(
                (new ItemInformation(consumables.getName(), consumables.getDescription(), consumables.getPurchasePrice(),
                        consumables.getSellingPrice()))));
        return itemInformationList.toArray(new ItemInformation[itemInformationList.size()]);

    }

    public void useItem(ItemInformation itemInformation){
        Consumable consumable = player.getInventory().getConsumables().stream().
                filter((Consumable cons) -> compare(itemInformation, cons)).
                findFirst().
                get();
        if(consumable != null){
            useConsumable(consumable);
            return;
        }

        Equipment equipment = player.getInventory().getEquipments().stream().
                filter((Equipment equip) -> compare(itemInformation, equip)).
                findFirst().
                get();
        if(consumable != null){
            changeEquip(equipment);
            return;
        }
    }

    private void useConsumable(Consumable consumable){
        player.getInventory().use(consumable);
    }

    private void changeEquip(Equipment equipment){
        if(equipment.getEquipmentType() == EquipmentType.ARMOR){
            if(player.getInventory().getArmor() == equipment){
                player.getInventory().takeOff(equipment);
            }else {
                player.getInventory().takeOn(equipment);
            }
        }else {
            if(player.getInventory().getWeapon() == equipment){
                player.getInventory().takeOff(equipment);
            }else {
                player.getInventory().takeOn(equipment);
            }
        }
    }

    private boolean compare(ItemInformation itemInfo, Item item) {
        return itemInfo.getName() == item.getName() &&
                itemInfo.getDescription() == item.getDescription() &&
                Integer.parseInt(itemInfo.getPurchasingPrice()) == item.getPurchasePrice() &&
                Integer.parseInt(itemInfo.getSellingPrice()) == item.getSellingPrice();
    }
}
