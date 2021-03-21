package Controllers;

import GachiCore.Components.Items.Base.Item;
import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.FloorHandler;
import GachiCore.GameHandlers.GachiHandler;
import GachiCore.GameHandlers.Floors.Base.Shop;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopController {
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private final FloorHandler floorHandler = FloorHandler.getInstance();
    private final GachiPowerUser user = gachiHandler.getHero();
    private Shop shop;
    private static final ShopController instance = new ShopController();
    private ShopController(){}
    public static ShopController getInstance() {
        return instance;
    }

    public boolean haveShop(){
        if(floorHandler.getCurrentFloor().getShop() == null) {
            shop = null;
            return false;
        }
        else {
            shop = floorHandler.getCurrentFloor().getShop();
            return true;
        }
    }

    public void buy(ItemInformation itemInformation){
        Item[] items = shop.getItems();
        Item item = Arrays.stream(items)
                .filter((Item currentItem) -> currentItem.getName() == itemInformation.getName())
                .findFirst()
                .get();
        if(shop.canBuy(item)){
            shop.buy(item);
        }
    }

    public void sell(ItemInformation itemInformation){
        ArrayList<Item> items = new ArrayList<>();
        user.getInventory().getEquipments().forEach((Item item) -> items.add(item));
        user.getInventory().getConsumables().forEach((Item item) -> items.add(item));

        Item item = items.stream()
                .filter((Item currentItem) -> currentItem.getName() == itemInformation.getName())
                .findFirst()
                .get();
        shop.sell(item);
    }
}
