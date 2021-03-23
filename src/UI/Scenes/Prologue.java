package UI.Scenes;

import UI.Controls.Base.InteractiveControl;
import UI.Controls.Button;
import UI.Controls.Label;
import UI.Controls.Panel;
import UI.Scenes.Base.Scene;

public class Prologue extends Scene {
    private Panel panel;
    private Scene menu;
    public Prologue(String text) {
        panel = new Panel();
        panel.setHeading("Prologue");

        Label context = new Label();
        context.setText(text);

        Button next = new Button();
        next.setText("Next");
        next.addClickListener((InteractiveControl control) -> next(control));

        panel.addControl(context);
        panel.addControl(next);

        panel.setX(32);
        panel.setY(5);
        panel.setRedraw(true);

        addControl(panel);
    }

    private void next(InteractiveControl control){
        if(menu == null) menu = SceneContainer.getScene("Fight");
        setCurrentScene(false);
        menu.setCurrentScene(true);
    }


}
