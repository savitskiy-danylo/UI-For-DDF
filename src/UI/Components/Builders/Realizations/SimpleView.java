package UI.Components.Builders.Realizations;

import UI.Components.ViewComponent.Borders;
import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.ViewComponent.ColorScheme;
import UI.Components.ViewComponent.View;

import java.awt.*;

public class SimpleView implements ViewBuilder {
    @Override
    public View build(String text) {
        //TODO Не забудь изменить цвет границ
        Borders borders = new Borders('+', '+', '+', '+', '-', '|');
        ColorScheme colorScheme = new ColorScheme(Color.pink, Color.WHITE, Color.PINK, Color.ORANGE);
        return new View(text, borders, colorScheme);
    }

    @Override
    public ColorScheme getActiveColorScheme() {
        //TODO Не забудь изменить цвет границ
        return new ColorScheme(Color.pink, Color.RED, Color.PINK, Color.ORANGE);
    }
}
