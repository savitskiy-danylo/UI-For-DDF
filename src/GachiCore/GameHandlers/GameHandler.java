package GachiCore.GameHandlers;

import GachiCore.Builders.BillyBuilder;
import GachiCore.Entities.Base.GachiPowerUser;

public class GameHandler {
    private static final GameHandler instance = new GameHandler();
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private GameHandler(){}

    public static GameHandler getInstance() {
        return instance;
    }

    public void startNewGame(){
        gachiHandler.setHero(new BillyBuilder().build());
        gachiHandler.loadCurrentFloor();
    }

    public void loadGame(GachiPowerUser user, int numberOfFloor){
        gachiHandler.setHero(user);
        FloorHandler.getInstance().setFloor(numberOfFloor);
        gachiHandler.loadCurrentFloor();
    }
}
