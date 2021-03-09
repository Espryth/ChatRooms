package me.espryth.chatrooms.events.chatroom;

import me.espryth.chatrooms.chatroom.ChatRoom;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ChatRoomEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ChatRoom chatRoom;

    public ChatRoomEvent(Player who, ChatRoom chatRoom) {
        super(who);
        this.chatRoom = chatRoom;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
