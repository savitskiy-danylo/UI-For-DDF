package UI.Components.Builders.Realizations;

import UI.Components.Borders;
import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.ColorScheme;
import UI.Components.View;

import java.awt.*;

public class SimpleView implements ViewBuilder {
    @Override
    public View build(String text) {
        Borders borders = new Borders('+', '+', '+', '+', '-', '|');
        ColorScheme colorScheme = new ColorScheme(Color.pink, Color.WHITE, Color.PINK, Color.ORANGE);
        return new View(text, borders, colorScheme);
    }
}
