package UI.Components.ViewComponent;

import java.awt.*;

public class ColorScheme {
    private final Color bgText, fgText, bgBorders, fgBorders;
    private final Color[] additionalColors;

    public ColorScheme(Color bgText, Color fgText, Color bgBorders, Color fgBorders, Color... additionalColors) {
        this.bgText = bgText;
        this.fgText = fgText;
        this.bgBorders = bgBorders;
        this.fgBorders = fgBorders;
        this.additionalColors = additionalColors;
    }

    public Color getBgText() {
        return bgText;
    }

    public Color getFgText() {
        return fgText;
    }

    public Color getBgBorders() {
        return bgBorders;
    }

    public Color getFgBorders() {
        return fgBorders;
    }

    public int getBgTextRgb(){ return bgText.getRGB(); }
    public int getFgTextRgb(){ return fgText.getRGB(); }
    public int getBgBorderRgb(){ return bgBorders.getRGB(); }
    public int getFgBorderRgb(){ return fgBorders.getRGB(); }
    public int[] getAdditionalColorsRgb(){
        int[] additionalColorsInt = new int[additionalColors.length];
        for(int index = 0; index < additionalColors.length; index++){
            additionalColorsInt[index] = additionalColors[index].getRGB();
        }
        return additionalColorsInt;
    }

    public Color[] getAdditionalColors() {
        return additionalColors;
    }
}
