package UI.Components.Builders.Realizations;

import UI.Components.ViewComponent.ColorScheme;
import UI.Components.ViewComponent.Borders;
import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.ViewComponent.View;

import java.awt.*;

public class BiosView implements ViewBuilder {
    @Override
    public View build(String text) {
        //TODO Не забудь изменить цвет границ
        Borders borders = new Borders('╔', '╗', '╚', '╝', '═', '║');
        ColorScheme colorScheme = new ColorScheme(Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE);
        return new View(text, borders, colorScheme);
    }

    @Override
    public ColorScheme getActiveColorScheme() {
        //TODO Не забудь изменить цвет границ
        return new ColorScheme(Color.BLUE, Color.RED, Color.BLUE, Color.WHITE);
    }
}
