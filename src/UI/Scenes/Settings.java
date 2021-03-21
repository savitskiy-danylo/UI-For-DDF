package UI.Scenes;

import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.Builders.Realizations.BiosView;
import UI.Components.Builders.Realizations.SimpleView;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Panel;
import UI.Options.CurrentView;
import UI.Scenes.Base.Scene;

public class Settings extends Scene {
    private final Panel settings;
    private Scene mainMenu;

    private final ViewBuilder biosView = new BiosView(), simpleView = new SimpleView();
    private final CurrentView currentView = CurrentView.getInstance();
    private int currentViewIndex = 1;

    public Settings() {
        settings = new Panel();

        Button changeStyle = new Button();
        changeStyle.setText("Change style");
        changeStyle.addClickListener((InteractiveControl control) -> changeStyle(control));

        Button back = new Button();
        back.setText("Back");
        back.addClickListener((InteractiveControl control) -> back(control));

        settings.addControl(changeStyle);
        settings.addControl(back);

        settings.setHeading("Settings");
        settings.setX(50);
        settings.setY(5);
        settings.setRedraw(true);

        addControl(settings);
    }

    private void back(InteractiveControl control){
        if(mainMenu == null) mainMenu = SceneContainer.getScene("MainMenu");
        setCurrentScene(false);
        mainMenu.setCurrentScene(true);
    }

    private void changeStyle(InteractiveControl control){
        if(currentViewIndex > 1) currentViewIndex = 0;
        switch (currentViewIndex){
            case 0 -> currentView.setBuilder(biosView);
            case 1 -> currentView.setBuilder(simpleView);
        }
        currentViewIndex++;
    }
}
