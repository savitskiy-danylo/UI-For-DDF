package GachiCore.AI;

public interface AIUser {
    public abstract void turn();
    public boolean wantSwap();
    public boolean wantAgreeWithSwap();
    public void positionChanged();
    public void setVanguard(boolean flag);
    public void nextTurn();
    public void newTurn();
}
