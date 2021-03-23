package UI.Scenes.Base;

import Controllers.PlayerController;
import Controllers.ShopController;
import Controllers.StatsController;
import UI.Actions;
import UI.Controls.Base.Control;
import UI.Controls.Base.InteractiveControl;
import UI.Controls.CommandLine;
import UI.Controls.Container;

import java.util.ArrayList;
import java.util.Arrays;

import static org.fusesource.jansi.Ansi.ansi;

public class Scene {
    protected final PlayerController playerController = PlayerController.getInstance();
    protected final ShopController shopController = ShopController.getInstance();
    protected final StatsController statsController = StatsController.getInstance();
    private ArrayList<Control> controls = new ArrayList<>();
    private ArrayList<InteractiveControl> interactiveControls = new ArrayList<>();
    protected ArrayList<Control> redraw = new ArrayList<>();
    private InteractiveControl currentFocus = null;
    protected final CommandLine commandLine = new CommandLine();
    private boolean isCurrentScene = false;
    private int index = 0;

    public void addControl(Control control){
        controls.add(control);
        if(control.isRedraw()) redraw.add(control);
        if(control instanceof Container) addContainer((Container) control);
        else searchAndAddInteractiveControls(control);
    }

    public void clearControls(){
        controls.clear();
        interactiveControls.clear();
        redraw.clear();

        index = 0;
        currentFocus = null;

    }

    public void refreshAutoRedrawControls(){
        ArrayList<Control> controlsForRemove = new ArrayList<>();
        redraw.stream().filter((Control control) -> !control.isRedraw()).forEach(controlsForRemove::add);
        controlsForRemove.forEach(redraw::remove);
    }

    private void addContainer(Container container){
        ArrayList<Control> controls = container.getControls();
        searchAndAddInteractiveControls(controls.toArray(new Control[controls.size()]));
    }

    private void searchAndAddInteractiveControls(Control... controls) {
        Arrays.stream(controls)
                .filter((Control control) -> control instanceof InteractiveControl)
                .forEach((Control control) -> interactiveControls.add((InteractiveControl) control));
        Arrays.stream(controls).filter(Control::isRedraw).forEach((Control control) -> redraw.add(control));
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

    public void draw(){
        eraseScreen();
        redraw.forEach(Control::draw);
        commandLine.draw();
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
