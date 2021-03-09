package me.espryth.chatrooms.events.chatroom;

import me.espryth.chatrooms.chatroom.ChatRoom;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class ChatRoomEnteredEvent extends ChatRoomEvent {

    public static final HandlerList HANDLER_LIST = new HandlerList();

    public ChatRoomEnteredEvent(Player who, ChatRoom chatRoom) {
        super(who, chatRoom);
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
