package UI.Components.Builders.Realizations;

import UI.Components.Borders;
import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.ColorScheme;
import UI.Components.View;

import java.awt.*;

public class BiosView implements ViewBuilder {
    @Override
    public View build(String text) {
        Borders borders = new Borders('╔', '╗', '╚', '╝', '═', '║');
        ColorScheme colorScheme = new ColorScheme(Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE);
        return new View(text, borders, colorScheme);
    }
}
