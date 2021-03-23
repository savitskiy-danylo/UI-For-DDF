package UI.Controls;

import UI.Controls.Base.Control;

import java.util.Comparator;

public class Panel extends Container {
    private Label heading = new Label();
    private int space = 0;

    {
        heading.setText("Panel");
    }

    @Override
    public void draw() {
        refreshSizes();

        heading.draw();
        view.draw();
        refreshPositionOfControls();
        controls.forEach(Control::draw);
    }

    public void setHeading(String text){
        heading.setText(text);
    }

    private void refreshPositionOfControls(){
        int newY = view.getY() + 1 + space;
        for (var control :
                controls) {
            control.setY(newY);
            control.setX(view.getX() + (widthMin - control.getWidth()) / 2 + 1);
            newY += control.getHeight() + space;
        }
    }

    private void refreshWidthMin(){
        widthMin = Math.max(heading.getWidthWithoutBorders(), controls.stream().max(Comparator.comparing(Control::getWidth)).get().getWidth());
    }

    private void refreshSizes(){
        refreshWidthMin();
        refreshHeightMin();

        view.setWidth(widthMin);
        heading.setWidth(widthMin);
        view.setHeight(heightMin - heading.getHeight() + 2);
    }

    @Override
    public int getHeight() {
        refreshHeightMin();
        return heightMin;
    }

    private void refreshHeightMin(){
        heightMin = heading.getHeight()
                + controls.stream().mapToInt(Control::getHeight).sum()
                + space * controls.size();
    }

    @Override
    public void setHeight(int height) {
    }

    @Override
    public int getWidth() {
        refreshWidthMin();
        return widthMin;
    }

    @Override
    public void setWidth(int width) {
        refreshWidthMin();
        super.setWidth(width);
        heading.setWidth(width);
    }

    @Override
    public void setX(int x) {
        heading.setX(x);
        view.setX(x);
        controls.forEach((Control control) -> control.setX(x + 1));
    }

    @Override
    public void setY(int y) {
        heading.setY(y);
        view.setY(y + heading.getHeight());
    }

    public void setSpace(int space) {
        if(space < 0) return;
        this.space = space;
    }
}
