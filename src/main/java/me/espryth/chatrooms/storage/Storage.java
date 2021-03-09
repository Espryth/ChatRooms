package me.espryth.chatrooms.storage;

import java.util.*;

public class Storage<T extends Storable> {

    private final Map<String, T> cache;

    public Storage() {
        this.cache = new HashMap<>();
    }

    public Optional<T> find(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    public Set<String> keys() {
        return cache.keySet();
    }

    public Map<String, T> getCache() {
        return cache;
    }
}
