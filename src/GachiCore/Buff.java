package GachiCore;

public abstract class Buff {
    protected String name, description;
    protected boolean refreshOnce = false, refreshEachAttack = false, refreshEachTurn = false;

    protected Entity target;

    public Buff(Entity target){
        this.target = target;
    }

    public boolean isBuff(BuffRefreshType refreshType){
        boolean answer = false;
        switch (refreshType){
            case ONCE -> answer = refreshOnce;
            case EACH_ATTACK -> answer = refreshEachAttack;
            case EACH_TURN -> answer = refreshEachTurn;
        }
        return answer;
    }

    public void takeOn(BuffRefreshType refreshType){
        switch (refreshType){
            case ONCE -> takeOnOnce();
            case EACH_ATTACK -> takeOnEachAttack();
            case EACH_TURN -> takeOnEachTurn();
        }
    }

    public void takeOff(BuffRefreshType refreshType){
        switch (refreshType){
            case ONCE -> takeOffOnce();
            case EACH_ATTACK -> takeOffEachAttack();
            case EACH_TURN -> takeOffEachTurn();
        }
    }

    protected void takeOnOnce(){}
    protected void takeOnEachAttack(){}
    protected void takeOnEachTurn(){}

    protected void takeOffOnce(){}
    protected void takeOffEachAttack(){}
    protected void takeOffEachTurn(){}




    public void setTarget(Entity target) {
        this.target = target;
    }

    //region Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    //endregion
}
