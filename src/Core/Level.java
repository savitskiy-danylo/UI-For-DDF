package Core;

import Core.Components.Base.Inventory;
import Core.EntitiesOLD.Base.Entity;

import java.util.ArrayList;

public class Level {
    private int number, money;
    private final ArrayList<Inventory> inventories = new ArrayList<>();
    public final ArrayList<Entity> entities = new ArrayList<>();

    public void addInventory(Inventory inventory){ inventories.add(inventory); }

}
