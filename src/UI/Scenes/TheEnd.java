package UI.Scenes;

import Main.StartPoint;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Label;
import UI.Controls.Panel;
import UI.Scenes.Base.Scene;

public class TheEnd extends Scene {
    private Panel menu;

    public TheEnd() {
        menu = new Panel();
        menu.setHeading("The End");

        Label text = new Label();
        text.setText("The end of Gachi journey.");

        Button exit = new Button();
        exit.setText("Exit");
        exit.addClickListener(this::exit);

        menu.addControl(text);
        menu.addControl(exit);

        menu.setX(45);
        menu.setY(5);

        menu.setRedraw(true);
        addControl(menu);
    }

    private void exit(InteractiveControl control) {
        StartPoint.onExit();
    }
}
