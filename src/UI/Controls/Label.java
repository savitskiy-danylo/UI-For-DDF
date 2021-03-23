package UI.Controls;

import UI.Components.ViewComponent.ColorScheme;
import UI.Controls.Base.Control;

public class Label extends Control {
    @Override
    public void draw() {
        view.draw();
    }

    public void setText(String text){
        view.setText(text);
        heightMin = view.getHeight();
        widthMin = view.getWidth();
    }

    public void setColorScheme(ColorScheme colorScheme){
        view.setColorScheme(colorScheme);
    }

    public int getWidthWithoutBorders(){
        return view.getWidthWithoutBorders();
    }
}
