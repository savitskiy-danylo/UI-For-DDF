package UI.Controls.Base;

import UI.Components.ViewComponent.ColorScheme;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class InteractiveControl extends Control{
    private ArrayList<Consumer<? super InteractiveControl>> clickEventListeners = new ArrayList<>();
    private ArrayList<Consumer<? super InteractiveControl>> focusChangedEventListeners = new ArrayList<>();
    private boolean focus = false;
    private ColorScheme activeColorScheme = currentView.getActiveColorScheme();
    private ColorScheme colorScheme = view.getColorScheme();

    @Override
    protected void refreshView() {
        if(refreshStyle){
            view.setBorders(currentView.getView("").getBorders());
            view.setColorScheme(currentView.getView("").getColorScheme());
            colorScheme = view.getColorScheme();
            activeColorScheme = currentView.getActiveColorScheme();
        }
        if(focus)
            view.setColorScheme(activeColorScheme);
    }

    public void addClickListener(Consumer<? super InteractiveControl> listener){
        clickEventListeners.add(listener);
    }

    public void addFocusChangedListener(Consumer<? super InteractiveControl> listener){
        focusChangedEventListeners.add(listener);
    }

    public void removeClickListener(Consumer<? super InteractiveControl> listener){
        clickEventListeners.remove(listener);
    }

    public void removeFocusChangedListener(Consumer<? super InteractiveControl> listener){
        focusChangedEventListeners.remove(listener);
    }

    public void setFocus(boolean flag){
        focus = flag;
        focusChangedEventListeners
                .forEach((Consumer<? super InteractiveControl> consumer) -> consumer.accept(this));
        if(focus) view.setColorScheme(activeColorScheme);
        else view.setColorScheme(colorScheme);
    }

    public void run(){
        clickEventListeners
                .forEach((Consumer<? super InteractiveControl> consumer) -> consumer.accept(this));
    }

}
