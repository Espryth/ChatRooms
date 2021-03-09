package me.espryth.chatrooms.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.espryth.chatrooms.chatroom.ChatRoom;
import me.espryth.chatrooms.storage.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    private Storage<ChatRoom> chatRoomStorage;

    public AsyncPlayerChatListener(Storage<ChatRoom> chatRoomStorage) {
        this.chatRoomStorage = chatRoomStorage;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChatOnChatRoom(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

        RegionQuery query = container.createQuery();
        Location location = BukkitAdapter.adapt(player.getLocation());

        ApplicableRegionSet set = query.getApplicableRegions(location);

        for (ProtectedRegion region : set) {

            chatRoomStorage.find(region.getId()).ifPresent(chatRoom -> {

                for(Player target : event.getRecipients()) {

                    Location locationTarget = BukkitAdapter.adapt(target.getLocation());
                    ApplicableRegionSet setTarget = query.getApplicableRegions(locationTarget);

                    if(setTarget.size() == 0) {
                        event.getRecipients().remove(target);
                        return;
                    }

                    for (ProtectedRegion regionTarget : setTarget) {

                        if(!regionTarget.getId().equals(chatRoom.getName())) {
                            event.getRecipients().remove(target);
                            return;
                        }

                        String format = chatRoom.getPrefix()
                                .replace("%player%", player.getName());

                        event.setFormat(format + "%2$s");

                    }
                }
            });
        }
    }
}
