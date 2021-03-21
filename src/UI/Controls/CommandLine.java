package UI.Controls;

import UI.Controls.Base.Control;

public class CommandLine extends Control {

    public CommandLine() {
        view.setText("Command Line: ");
        setX(0);
        setY(28);
        setWidth(view.getWidth() + 5);
        redraw = true;
    }

    @Override
    public void draw() {
        view.draw();
    }
}
