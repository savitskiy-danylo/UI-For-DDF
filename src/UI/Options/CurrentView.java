package UI.Options;

import UI.Components.Builders.Interfaces.ViewBuilder;
import UI.Components.Builders.Realizations.BiosView;
import UI.Components.ViewComponent.ColorScheme;
import UI.Components.ViewComponent.View;


import java.util.ArrayList;

public class CurrentView {
    private static final CurrentView currentView = new CurrentView();
    private final ArrayList<Runnable> listeners = new ArrayList<>();
    private ViewBuilder viewBuilder = new BiosView();
    private ColorScheme colorScheme;
    private CurrentView(){}
    public static CurrentView getInstance(){ return currentView; }
    public View getView(String text){ return viewBuilder.build(text); }
    public ColorScheme getActiveColorScheme() { return viewBuilder.getActiveColorScheme(); }
    public void setBuilder(ViewBuilder viewBuilder) {
        this.viewBuilder = viewBuilder;
        listeners.forEach(Runnable::run);
    }
    public void addListener(Runnable runnable){
        listeners.add(runnable);
    }

    public void removeListener(Runnable runnable){
        listeners.remove(runnable);
    }
}
