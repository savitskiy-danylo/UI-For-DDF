package UI.Components.ViewComponent;

import static org.fusesource.jansi.Ansi.ansi;

public class Borders {
    private final char downRight, downLeft, upRight, upLeft, horizontal, vertical;
    private final char[] additionalBorders;
    private String spaceLine = "", horizontalLine = "";

    public Borders(char downRight, char downLeft, char upRight, char upLeft, char horizontal, char vertical, char... additionalBorders) {
        this.downRight = downRight;
        this.downLeft = downLeft;
        this.upRight = upRight;
        this.upLeft = upLeft;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.additionalBorders = additionalBorders;
    }

    public void draw(int x, int y, int height, int width){
        final int yMax = y + height;

        refreshLines(width);

        System.out.print(ansi().cursor(y,x).a(downRight).a(horizontalLine).a(downLeft));
        for (int indexY = y+1; indexY < yMax-1; indexY++) {
            System.out.print(ansi().cursor(indexY, x)
                    .a(vertical + spaceLine + vertical));
        }
        System.out.print(ansi().cursor(yMax-1,x).a(upRight).a(horizontalLine).a(upLeft));
    }

    private void refreshLines(int width){
        final int widthOfLine = width - 2;
        if(spaceLine.length() != widthOfLine) {
            spaceLine = getSpaceLine(widthOfLine, ' ');
        }
        if(horizontalLine.length() != widthOfLine) {
            horizontalLine = getSpaceLine(widthOfLine, horizontal);
        }
    }

    private String getSpaceLine(int width, char symbol){
        return String.format("%" + width + "." + width + "s", " ").replace(' ', symbol);
    }

    public char getDownRight() {
        return downRight;
    }

    public char getDownLeft() {
        return downLeft;
    }

    public char getUpRight() {
        return upRight;
    }

    public char getUpLeft() {
        return upLeft;
    }

    public char getHorizontal() {
        return horizontal;
    }

    public char getVertical() {
        return vertical;
    }

    public char[] getAdditionalBorders() {
        return additionalBorders;
    }
}
