package UI.Scenes;

import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Scenes.Base.Scene;

public class MainMenu extends Scene {
    private Panel menu;
    private Scene settings;

    public MainMenu() {
        menu = new Panel();
        menu.setHeading("Menu");

        Button play = new Button();
        play.setText("Play");

        Button settings = new Button();
        settings.setText("Settings");
        settings.addClickListener((InteractiveControl control) -> displaySettings(control));

        Button exit = new Button();
        exit.setText("Exit");

        menu.addControl(play);
        menu.addControl(settings);
        menu.addControl(exit);

        menu.setX(50);
        menu.setY(5);

        addControl(menu);

        setCurrentScene(true);
    }

    private void displaySettings(InteractiveControl control){
        if(settings == null) settings = SceneContainer.getScene("Settings");
        setCurrentScene(false);
        settings.setCurrentScene(true);
    }


    @Override
    protected void draw() {
        eraseScreen();
        menu.draw();
    }
}
