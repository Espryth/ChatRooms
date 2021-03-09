package me.espryth.chatrooms;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatRoomsPlugin extends JavaPlugin {
    private PluginCore core;

    @Override
    public void onLoad() {
        this.core = new ChatRoomsCore(this);
    }

    @Override
    public void onEnable() {
        this.core.initPlugin();
    }

    @Override
    public void onDisable() {
        this.core.disablePlugin();
    }

    public PluginCore getCore() {
        return core;
    }
}
