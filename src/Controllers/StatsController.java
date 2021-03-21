package Controllers;

import GachiCore.Entities.Base.GachiPowerUser;
import GachiCore.GameHandlers.GachiHandler;

public class StatsController {
    private final GachiHandler gachiHandler = GachiHandler.getInstance();
    private final GachiPowerUser player = gachiHandler.getHero();
    private static final StatsController instance = new StatsController();
    private StatsController(){}
    public static StatsController getInstance() {
        return instance;
    }

    public String getName(){
        return player.getName();
    }

    public String getDescription(){
        return player.getDescription();
    }

    public String getAgilityCurrent(){
        return intToString(player.getStats().getAgility());
    }

    public String getAgilityMax(){
        return intToString(player.getStats().getAgilityMax());
    }

    public String getStrengthCurrent(){
        return intToString(player.getStats().getStrength());
    }

    public String getStrengthMax(){
        return intToString(player.getStats().getStrengthMax());
    }

    public String getMoney(){
        return intToString(player.getInventory().getMoney());
    }

    public String getGachiPowerCurrent(){
        return intToString(player.getGachiPower().getGachiPowerCurrent());
    }

    public String getGachiPowerMax(){
        return intToString(player.getGachiPower().getGachiPowerMax());
    }

    public String getDamageRange(){
        return intToString(player.getStats().getDamageMax()) + "-" + intToString(player.getStats().getDamageMax());
    }

    private String intToString(int number){
        return Integer.toString(number);
    }

}
