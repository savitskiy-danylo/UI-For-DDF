package UI.Controls;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import UI.Components.ViewComponent.ColorScheme;
import UI.Controls.Base.Control;

import java.awt.*;

public class Chat extends Control {
    private MessageBox messageBox = MessageBox.getInstance();
    private ColorScheme positive = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.GREEN, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders()),
            negative = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.RED, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders()),
            neutral = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.DARK_GRAY, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders()),
            system = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.GRAY, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders());
    public Chat() {
        setX(0);
        setY(25);
        messageBox.addNewMessageEventListener(this::draw);
    }

    @Override
    public void draw() {
        Message message = messageBox.getMessage();
        switch (message.getMessageType()){
            case SYSTEM -> view.setColorScheme(system);
            case NEUTRAL -> view.setColorScheme(neutral);
            case NEGATIVE -> view.setColorScheme(negative);
            case POSITIVE -> view.setColorScheme(positive);
        }
        view.setText(message.getText());
        view.draw();
    }

    @Override
    protected void refreshView() {
        super.refreshView();
        if(isRefreshStyle()){
            positive = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.GREEN, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders());
            negative = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.RED, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders());
            neutral = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.DARK_GRAY, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders());
            system = new ColorScheme(view.getColorScheme().getBgText(),
                            Color.GRAY, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders());
        }
    }
}
