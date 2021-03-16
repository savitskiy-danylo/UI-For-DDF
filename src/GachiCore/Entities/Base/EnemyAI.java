package GachiCore.Entities.Base;

public abstract class EnemyAI {
    private Enemy target;

    private boolean wantSwap, wantSkip;

    public EnemyAI(Enemy target) {
        this.target = target;
    }

    public boolean isWantSwap() {
        return wantSwap;
    }

    public void setWantSwap(boolean wantSwap) {
        this.wantSwap = wantSwap;
    }

    public boolean isWantSkip() {
        return wantSkip;
    }

    public void setWantSkip(boolean wantSkip) {
        this.wantSkip = wantSkip;
    }

    public abstract void turn();
}
