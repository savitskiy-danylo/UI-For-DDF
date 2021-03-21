package GachiCore.GameHandlers;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import GachiCore.Entities.Base.GachiPowerUser;

public class GachiHandler {
    private static final GachiHandler gachiHandler = new GachiHandler();
    private GachiPowerUser hero;
    private FloorHandler floorHandler = FloorHandler.getInstance();
    private Floor currentFloor;
    private MessageBox messageBox = MessageBox.getInstance();

    private GachiHandler() {
    }
    public static GachiHandler getInstance(){ return gachiHandler; }

    private void getNextFloor(){
        currentFloor = floorHandler.getNextFloor();
    }

    public void nextTurn(){
        if(floorIsClear()) return;
        hero.nextTurn();

        currentFloor.turn();
        currentFloor.nextTurn();

        hero.newTurn();
        currentFloor.newTurn();

    }

    public void nextFloor(){
        getNextFloor();
        if(currentFloor == null) messageBox.addNewMessage(new Message("Game over!", MessageType.SYSTEM));
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
