package UI.Controls;


import Controllers.StatsController;
import UI.Controls.Base.Control;

public class Stats extends Control {
    private final StatsController statsController = StatsController.getInstance();
    @Override
    public void draw() {
        view.setText("HP: " + statsController.getStrengthCurrent() + "/" + statsController.getStrengthMax() + "\n" +
                "Damage: " + statsController.getDamageRange() + "\n" +
                "Armor: " + statsController.getArmor() + "\n" +
                "Dodge: " + statsController.getAgilityCurrent() + "\n" +
                "Money: " + statsController.getMoney() + "\n" +
                "AP: " + statsController.getAP());
        view.draw();
    }
}
