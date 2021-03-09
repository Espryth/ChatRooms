package me.espryth.chatrooms.listeners.region;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.Handler;
import me.espryth.chatrooms.events.RegionEnteredEvent;
import me.espryth.chatrooms.events.RegionLeftEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.Set;
public class EntryOnRegionListener extends Handler implements Listener {

    public final PluginManager pm = Bukkit.getPluginManager();
    public static final Factory factory = new Factory();

    public static class Factory extends Handler.Factory<EntryOnRegionListener> {

        @Override
        public EntryOnRegionListener create(Session session) {
            return new EntryOnRegionListener(session);
        }
    }

    public EntryOnRegionListener(Session session) {
        super(session);
    }

    @Override
    public boolean onCrossBoundary(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Set<ProtectedRegion> entered, Set<ProtectedRegion> left, MoveType moveType)
    {

        for(ProtectedRegion r : entered) {
            RegionEnteredEvent regentered = new RegionEnteredEvent(player.getUniqueId(), r);
            pm.callEvent(regentered);
            if(regentered.isCancelled()) return false;
        }


        for(ProtectedRegion r : left) {
            RegionLeftEvent regleft = new RegionLeftEvent(player.getUniqueId(), r);
            pm.callEvent(regleft);
            if(regleft.isCancelled()) return false;
        }
        return true;
    }





}