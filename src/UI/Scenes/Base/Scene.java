package UI.Scenes.Base;

import UI.Actions;
import UI.Controls.Base.Control;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.Container;

import java.util.ArrayList;
import java.util.Arrays;

import static org.fusesource.jansi.Ansi.ansi;

public class Scene {
    private ArrayList<Control> controls = new ArrayList<>();
    private ArrayList<InteractiveControl> interactiveControls = new ArrayList<>();
    private InteractiveControl currentFocus = null;
    private boolean isCurrentScene = false;
    private int index = 0;

    public void addControl(Control control){
        controls.add(control);
        if(control instanceof Container) addContainer((Container) control);
        else searchAndAddInteractiveControls(control);
    }

    private void addContainer(Container container){
        ArrayList<Control> controls = container.getControls();
        searchAndAddInteractiveControls(controls.toArray(new Control[controls.size()]));
    }

    private void searchAndAddInteractiveControls(Control... controls) {
        Arrays.stream(controls)
                .filter((Control control) -> control instanceof InteractiveControl)
                .forEach((Control control) -> interactiveControls.add((InteractiveControl) control));
        if (currentFocus == null && !interactiveControls.isEmpty()) {
            currentFocus = interactiveControls.get(index);
            currentFocus.setFocus(true);
        }
    }

    public void action(Actions action){
        switch (action){
            case OK -> ok();
            case UP -> up();
            case DOWN -> down();
        }
        if(isCurrentScene) draw();
    }

    private void ok(){
        if(currentFocus != null) currentFocus.run();
    }

    private void up(){
        index--;
        currentFocus.setFocus(false);
        if(index == -1) index = interactiveControls.size() - 1;
        currentFocus = interactiveControls.get(index);
        currentFocus.setFocus(true);
    }

    private void down(){
        index++;
        currentFocus.setFocus(false);
        if(index == interactiveControls.size()) index = 0;
        currentFocus = interactiveControls.get(index);
        currentFocus.setFocus(true);
    }

    protected void draw(){

    }

    public boolean isCurrentScene() {
        return isCurrentScene;
    }

    public void setCurrentScene(boolean currentScene) {
        isCurrentScene = currentScene;
        eraseScreen();
        draw();
    }

    protected void eraseScreen(){
        System.out.println(ansi().eraseScreen());
    }
}
