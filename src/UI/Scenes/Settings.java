package UI.Scenes;

import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.Scene;

public class Settings extends Scene {
    private final Panel settings;
    public Settings() {
        settings = new Panel();

        Button changeStyle = new Button();
        changeStyle.setText("Change style");

        settings.addControl(changeStyle);

        settings.setHeading("Settings");
        settings.setX(50);
        settings.setY(5);

        addControl(settings);
    }

    @Override
    protected void draw() {
        eraseScreen();
        settings.draw();
    }
}
