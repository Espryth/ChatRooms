package me.espryth.chatrooms;

import com.sk89q.worldguard.WorldGuard;
import me.espryth.chatrooms.chatroom.ChatRoom;
import me.espryth.chatrooms.command.MainCommand;
import me.espryth.chatrooms.file.YamlFile;
import me.espryth.chatrooms.listeners.AsyncPlayerChatListener;
import me.espryth.chatrooms.listeners.region.EntryOnRegionListener;
import me.espryth.chatrooms.listeners.region.RegionEnteredListener;
import me.espryth.chatrooms.listeners.region.RegionLeftListener;
import me.espryth.chatrooms.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ChatRoomsCore implements PluginCore {

    private YamlFile config;
    private Storage<ChatRoom> chatRoomStorage;

    private final ChatRoomsPlugin plugin;

    public ChatRoomsCore(ChatRoomsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void initPlugin() {
        this.initObjects();
        this.initChatRooms();
        this.initListeners();
        this.initCommands();
    }

    @Override
    public void disablePlugin() {
        unloadChatRooms();
    }

    @Override
    public void reloadPlugin() {
        this.chatRoomStorage = new Storage<>();
        initChatRooms();
    }

    public void initChatRooms() {
        for(String key : config.getConfigurationSection("chatrooms").getKeys(false)) {

            ChatRoom chatRoom = new ChatRoom(config, key);
            chatRoomStorage.getCache().put(key, chatRoom);

        }
    }
    public void unloadChatRooms() {
        for(String key : chatRoomStorage.keys()) {

            chatRoomStorage.find(key).ifPresent(chatRoom ->
                    config.set("chatrooms." + chatRoom.getName(), chatRoom.serialize())
            );

        }
    }

    private void initObjects() {
        this.chatRoomStorage = new Storage<>();

        this.config = new YamlFile(plugin, "config");
    }

    private void initListeners() {
        loadListeners(
                new RegionEnteredListener(chatRoomStorage),
                new RegionLeftListener(chatRoomStorage),
                new AsyncPlayerChatListener(chatRoomStorage)
        );

        if (!WorldGuard.getInstance().getPlatform().getSessionManager().registerHandler(EntryOnRegionListener.factory, null)) {
            plugin.getLogger().severe("Error to load WorldGuard API");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }

    }

    private void loadListeners(Listener... listeners) {
        for(Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    private void initCommands() {
        plugin.getCommand("chatrooms").setExecutor(new MainCommand(plugin));
    }

    @Override
    public YamlFile getConfig() {
        return config;
    }

    @Override
    public Storage<ChatRoom> getChatRoomStorage() {
        return chatRoomStorage;
    }

}
