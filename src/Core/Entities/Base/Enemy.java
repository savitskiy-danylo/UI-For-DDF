package Core.Entities.Base;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Enemy extends Entity{
    private boolean isRange = false;
    private boolean wantSwap = false;
    private boolean wantSkip = true;
    private ArrayList<Enemy> allies = null;

    public void addAllies(Enemy... enemies){
        allies = new ArrayList<>(Arrays.asList(enemies));
    }

    public boolean isRange() {
        return isRange;
    }

    public boolean isWantSwap() {
        return wantSwap;
    }

    public boolean isWantSkip() {
        return wantSkip;
    }
}
