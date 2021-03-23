package UI.Controls;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import UI.Components.ViewComponent.ColorScheme;
import UI.Controls.Base.Control;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Chat extends Control {
    private MessageBox messageBox = MessageBox.getInstance();
    private Stack<String> textOfMessages = new Stack<>();
    private Stack<Label> labels = new Stack<>();
    private final int countOfMessages = 3;
    private int lastY = 28;
    private ColorScheme positive = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.GREEN, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders()),
            negative = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.RED, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders()),
            neutral = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.DARK_GRAY, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders()),
            system = new ColorScheme(view.getColorScheme().getBgText(),
                    Color.GRAY, view.getColorScheme().getBgBorders(), view.getColorScheme().getFgBorders());
    public Chat() {
        messageBox.addNewMessageEventListener(this::newMessage);
    }

    @Override
    public void draw() {
        Message message = messageBox.getMessage();
        int width = labels.stream().mapToInt(Label::getWidth).max().getAsInt();
        int height = labels.stream().mapToInt(Label::getWidth).sum();
        if(getY() + height > lastY) setY(lastY - height);
        int y = getY() + 1;
        int x = getX() + 1;
        for (var label :
                labels) {
            label.setY(y);
            label.setX(x + (width - label.getWidth())/2 + 1);
            label.draw();

            y += label.getY();
        }
        view.setText(message.getText());
        view.draw();
    }

    private void newMessage(){
        String text = messageBox.getMessage().getText();
        textOfMessages.push(text);
        Label label = new Label();
        label.setText(text);
        label.setColorScheme(getColorScheme());
        labels.push(label);
        if(labels.size() > countOfMessages) labels.pop();
        draw();
    }

    private ColorScheme getColorScheme(){
        Message message = messageBox.getMessage();
        ColorScheme colorScheme = null;
        switch (message.getMessageType()){
            case SYSTEM -> colorScheme = system;
            case NEUTRAL -> colorScheme = neutral;
            case NEGATIVE -> colorScheme = negative;
            case POSITIVE -> colorScheme = positive;
        }
        return colorScheme;
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

    public void setLastY(int y){
        lastY = y;
    }
}
