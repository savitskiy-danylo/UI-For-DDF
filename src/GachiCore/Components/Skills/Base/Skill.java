package GachiCore.Components.Skills.Base;

import GachiCore.Entities.Base.Entity;

public abstract class Skill {

    private Entity user;
    private String name, description;
    private int price;

    public Skill(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setUser(Entity user) {
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

    public abstract void use();
}
