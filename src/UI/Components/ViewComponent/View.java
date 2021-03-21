package UI.Components.ViewComponent;

import static org.fusesource.jansi.Ansi.ansi;

public class View {
    private String text;
    private String[] mutateText;
    private int width, height, x = 1, y = 1;
    private int widthMax;
    private Borders borders;

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    private ColorScheme colorScheme;

    public View(String text, Borders borders, ColorScheme colorScheme){
        setText(text);
        this.borders = borders;
        this.colorScheme = colorScheme;
    }

    public void draw(){
        System.out.print(ansi()
                .bgRgb(colorScheme.getBgBorderRgb())
                .fgRgb(colorScheme.getFgBorderRgb()));
        borders.draw(x, y, height, width+2);
        int index = 1;
        for (var line :
                mutateText) {
            System.out.print(ansi()
                    .bgRgb(colorScheme.getBgTextRgb())
                    .fgRgb(colorScheme.getFgTextRgb())
                    .cursor(y+index, x + (width - line.length())/2 + 1)
                    .a(line));
            index++;
        }
        System.out.print(ansi().fgDefault().bgDefault());
    }

    private void refreshMutateText(){
        mutateText = text.split("\n");
    }

    private int getMaxCountOfSymbols(){
        int maxLength = 0;
        for (var line :
                mutateText) {
            if(maxLength < line.length()) maxLength = line.length();
        }

        return maxLength;
    }

    private void refreshWidth(){
        int maxLength = getMaxCountOfSymbols();
        if(width - 2 < maxLength) width = maxLength + 2;
    }
    private void refreshHeight(){
        int data = 1;
        for (var symbol :
                text.toCharArray()) {
            if(symbol == '\n') data++;
        }
        if(data > height-2) height = data+2;
    }

    public void setWidth(int width) {
        this.width = width;
        refreshWidth();
    }

    public void setHeight(int height) {
        if(height < 3) return;
        this.height = height;
        refreshWidth();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return widthMax;
    }

    public int getHeight() {
        return height;
    }

    public void setBorders(Borders borders) {
        this.borders = borders;
    }

    public Borders getBorders() {
        return borders;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        refreshMutateText();
        refreshWidth();
        refreshHeight();
        widthMax = width + 2;
    }
}