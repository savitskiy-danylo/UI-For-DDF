package UI.Controls;

import UI.Controls.Base.Control;

import java.util.ArrayList;

public abstract class Container extends Control {
    protected ArrayList<Control> controls = new ArrayList<>();

    public void addControl(Control control){
        controls.add(control);
    }

    public void removeControl(Control control){
        controls.remove(control);
    }

    public ArrayList<Control> getControls() {
        return controls;
    }
    public void clear(){ controls.clear(); }
}
