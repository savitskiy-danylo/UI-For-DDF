package UI.Components.Builders.Interfaces;

import UI.Components.ViewComponent.ColorScheme;
import UI.Components.ViewComponent.View;

public interface ViewBuilder {
    abstract View build(String text);
    abstract ColorScheme getActiveColorScheme();
}
