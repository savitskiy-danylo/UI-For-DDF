package UI.Scenes.Base;

import UI.Controls.Base.Control;
import UI.Controls.Chat;
import UI.Controls.Stats;

public class GameScene extends Scene{
    private Chat chat = new Chat();
    private Stats stats = new Stats();
    @Override
    public void draw() {
        eraseScreen();
        redraw.forEach(Control::draw);
        chat.draw();
        stats.draw();
        commandLine.draw();
    }
}
