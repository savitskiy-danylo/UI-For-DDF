package UI.Controls;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import UI.Components.ViewComponent.ColorScheme;
import UI.Controls.Base.Control;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class Chat extends Control {
    private MessageBox messageBox = MessageBox.getInstance();
    private LinkedList<Label> labels = new LinkedList<>();
    private final int countOfMessages = 3;
    private int lastY = 27;
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
        if(labels.isEmpty()) return;
        int width = labels.stream().mapToInt(Label::getWidth).max().getAsInt();
        int height = labels.stream().mapToInt(Label::getHeight).sum();
        view.setWidth(width);
        view.setHeight(height + 2);
        setY(lastY - height - 2);
        int y = getY() + 1;
        int x = getX();
        view.draw();
        for (var label :
                labels) {
            label.setY(y);
            label.setX(x + (width - label.getWidth())/2 + 1);
            label.draw();

            y += label.getHeight();
        }
    }

    private void newMessage(){
        String text = messageBox.getMessage().getText();
        Label label = new Label();
        label.setText(text);
        label.setColorScheme(getColorScheme());
        labels.add(label);
        if(labels.size() > countOfMessages) labels.removeFirst();
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
