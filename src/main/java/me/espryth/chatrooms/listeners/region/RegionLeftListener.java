package me.espryth.chatrooms.listeners.region;

import me.espryth.chatrooms.chatroom.ChatRoom;
import me.espryth.chatrooms.events.RegionEnteredEvent;
import me.espryth.chatrooms.events.chatroom.ChatRoomLeftEvent;
import me.espryth.chatrooms.storage.Storage;
import me.espryth.chatrooms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RegionLeftListener implements Listener {

    private final Storage<ChatRoom> chatRoomStorage;

    public RegionLeftListener(Storage<ChatRoom> chatRoomStorage) {
        this.chatRoomStorage = chatRoomStorage;
    }

    @EventHandler
    public void onJoinToRegion(RegionEnteredEvent event) {

        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null)
            return;

        String regionName = event.getRegionName();

        chatRoomStorage.find(regionName).ifPresent(chatRoom ->  {

            String[] values = chatRoom.getLeftTitle().split(";");
            player.sendTitle(
                    Utils.colorize(values[0]),
                    Utils.colorize(values[1]),
                    Integer.parseInt(values[2]),
                    Integer.parseInt(values[3]),
                    Integer.parseInt(values[4])
            );

            Bukkit.getPluginManager().callEvent(new ChatRoomLeftEvent(player, chatRoom));

        });
    }
}
