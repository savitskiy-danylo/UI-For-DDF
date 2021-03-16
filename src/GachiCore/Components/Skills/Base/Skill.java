package GachiCore.Components.Skills.Base;

import GachiCore.Entities.Base.Entity;
import GachiCore.Entities.Base.GachiPowerUser;

public abstract class Skill {

    protected GachiPowerUser user;
    private String name, description;
    private int price;

    public Skill(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setUser(GachiPowerUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
    public boolean canUse(){
        return user.getGachiPower().isEnoughGachiPower(price);
    }
    public abstract void use();
}
