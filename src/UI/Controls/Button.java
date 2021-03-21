package UI.Controls;

import Controllers.ItemInformation;
import UI.Controls.Base.InteractiveControl;

public class Button extends InteractiveControl {
    private ItemInformation itemInformation = null;

    {
        view.setText("Button");
    }

    @Override
    public void draw() {
        view.draw();
    }

    public void setText(String text){
        view.setText(text);
        heightMin = view.getHeight();
        widthMin = view.getWidth();
    }

    public ItemInformation getItemInformation() {
        return itemInformation;
    }

    public void setItemInformation(ItemInformation itemInformation) {
        this.itemInformation = itemInformation;
    }
}
