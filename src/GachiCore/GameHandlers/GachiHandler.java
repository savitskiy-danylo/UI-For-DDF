package GachiCore.GameHandlers;

import GachiCore.Entities.Base.GachiPowerUser;

public class GachiHandler {
    private static final GachiHandler gachiHandler = new GachiHandler();
    private GachiPowerUser hero;
    private FloorHandler floorHandler = FloorHandler.getInstance();
    private Floor currentFloor;
    private GachiHandler() {
    }
    public static GachiHandler getInstance(){ return gachiHandler; }


    public void leftSide(){
        currentFloor = floorHandler.getLeftSide();
    }

    public void rightSide(){
        currentFloor = floorHandler.getRightSide();
    }

    public void nextTurn(){
        if(floorIsClear()) return;
        hero.nextTurn();

        currentFloor.turn();
        currentFloor.nextTurn();

        hero.newTurn();
        currentFloor.newTurn();

    }

    public boolean floorIsClear(){
        return currentFloor.isClear();
    }

    public GachiPowerUser getHero() {
        return hero;
    }

    public void setHero(GachiPowerUser hero) {
        this.hero = hero;
    }
}
