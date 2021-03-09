package me.espryth.chatrooms.listeners;

import me.espryth.chatrooms.chatroom.ChatRoom;
import me.espryth.chatrooms.events.chatroom.ChatRoomEnteredEvent;
import me.espryth.chatrooms.events.chatroom.ChatRoomLeftEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatRoomListener implements Listener {

    @EventHandler
    public void onChatRoomEntered(ChatRoomEnteredEvent event) {

        Player player = event.getPlayer();
        ChatRoom chatRoom = event.getChatRoom();

    }

    @EventHandler
    public void onChatRoomLeft(ChatRoomLeftEvent event) {

        Player player = event.getPlayer();
        ChatRoom chatRoom = event.getChatRoom();

    }
}
