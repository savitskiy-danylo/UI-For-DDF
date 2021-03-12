import Core.Message;
import Core.MessageBox;
import Core.MessageType;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {
    public static void main(String[] args){
        MessageBox messageBox = MessageBox.getInstance();
        messageBox.addNewMessageEventListener(() -> newMessage());
        messageBox.addNewMessage(new Message("Hello!", MessageType.SYSTEM));
    }

    public static void newMessage(){
        System.out.print("New message! Message text: " + MessageBox.getInstance().getMessage().getText());

    }
}
/*
        AnsiConsole.systemInstall();
        System.out.print(ansi().eraseScreen());
        ViewBuilder viewBuilder = new BiosView();
        View view = viewBuilder.build("Button");
        view.draw();
        new Scanner(System.in).nextLine();
        AnsiConsole.systemUninstall();
* */