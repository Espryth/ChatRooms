package me.espryth.chatrooms.events;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class RegionEnteredEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled = false;

    private final UUID uuid;
    private final ProtectedRegion region;
    private final String regionName;

    public RegionEnteredEvent(UUID playerUUID, ProtectedRegion region)
    {
        this.uuid = playerUUID;
        this.region = region;
        this.regionName = region.getId();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public String getRegionName() {
        return regionName;
    }

    public ProtectedRegion getRegion() {
        return region;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled=cancelled;
    }
}