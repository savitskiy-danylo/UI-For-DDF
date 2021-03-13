package Core;

public abstract class SuperAttack {
    protected int price;
    protected String name, description;
    public abstract int getDamage(int baseDamage);
    public boolean canUse(int actionPoints){
        //TODO сообщение про нехватку GP
        return actionPoints - price >= 0;
    }
}
