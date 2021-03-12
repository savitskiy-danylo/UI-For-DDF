package Core;

public class Message {
    private final String message;
    private final MessageType messageType;

    public Message(String message, MessageType messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getText() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
