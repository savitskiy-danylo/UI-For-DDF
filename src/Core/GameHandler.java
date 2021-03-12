package Core;

public class GameHandler {
    private static final GameHandler gameHandler = new GameHandler();
    private GameHandler(){ }
    public static GameHandler getInstance(){ return gameHandler; }
}
