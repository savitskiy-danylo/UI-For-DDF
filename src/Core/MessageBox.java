package Core;

import java.util.ArrayList;

public class MessageBox {
    private final ArrayList<NewMessageEventListener> messageListeners = new ArrayList<>();
    private final ArrayList<Message> messages = new ArrayList<>();

    public void addNewMessage(Message message){
        messages.add(message);
        for (var listener :
                messageListeners) {
            listener.newMessageEvent();
        }
    }

    public Message getMessage(){
        return messages.get(messages.size()-1);
    }

    public void addNewMessageEventListener(NewMessageEventListener messageListener){
        messageListeners.add(messageListener);
    }

    public void deleteNewMessageEventListener(NewMessageEventListener messageListener){
        messageListeners.remove(messageListener);
    }

    private MessageBox(){}

    private static MessageBox messageBox = new MessageBox();
    public static MessageBox getInstance(){ return messageBox; }
}
