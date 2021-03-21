package UI.Controls.Base;

import UI.Components.ViewComponent.View;
import UI.Options.CurrentView;

public abstract class Control {
    protected int heightMin, widthMin;
    protected CurrentView currentView = CurrentView.getInstance();
    protected View view = currentView.getView("");
    protected boolean refresh = true;

    {
        currentView.addListener(() -> refreshView());
    }

    protected void refreshView(){
        if(refresh){
            view = currentView.getView("");
        }
    }

    public int getX() {
        return view.getX();
    }

    public int getY() {
        return view.getY();
    }

    public int getHeight() {
        return view.getHeight();
    }

    public int getWidth() {
        return view.getWidth();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setX(int x) {
        view.setX(x);
    }

    public void setY(int y) {
        view.setY(y);
    }

    public void setHeight(int height) {
        if(height < heightMin) return;
        view.setHeight(height);
    }

    public void setWidth(int width) {
        if(width < widthMin) return;
        view.setWidth(width);
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public abstract void draw();
}
