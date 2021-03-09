package me.espryth.chatrooms;

import me.espryth.chatrooms.chatroom.ChatRoom;
import me.espryth.chatrooms.file.YamlFile;
import me.espryth.chatrooms.storage.Storage;

public interface PluginCore {

    void initPlugin();
    void disablePlugin();
    void reloadPlugin();
    YamlFile getConfig();
    Storage<ChatRoom> getChatRoomStorage();

}
