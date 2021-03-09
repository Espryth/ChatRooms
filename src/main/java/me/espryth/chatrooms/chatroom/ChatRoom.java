package me.espryth.chatrooms.chatroom;

import me.espryth.chatrooms.file.YamlFile;
import me.espryth.chatrooms.storage.Storable;

import java.util.HashMap;
import java.util.Map;

public class ChatRoom implements Storable {

    private final String name;
    private final String prefix;
    private final String joinTitle;
    private final String leftTitle;

    public ChatRoom(YamlFile file, String name) {
        this.name = name;
        this.prefix = file.getString("chatrooms." + name + ".prefix");
        this.joinTitle = file.getString("chatrooms." + name + ".joinTitle");
        this.leftTitle = file.getString("chatrooms." + name + ".leftTitle");
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getJoinTitle() {
        return joinTitle;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    @Override
    public Map<String, Object> serialize() {

        Map<String, Object> serializeMap = new HashMap<>();

        serializeMap.put("prefix", prefix);
        serializeMap.put("joinTitle", joinTitle);
        serializeMap.put("leftTitle", leftTitle);

        return serializeMap;
    }
}
