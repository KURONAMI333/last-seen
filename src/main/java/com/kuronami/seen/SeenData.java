package com.kuronami.seen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * Persistent name → last few seen-timestamps (epoch millis), newest
 * first, capped per player. Backed by vanilla {@link SavedData} so it
 * survives restarts. Lives in {@code world/data/seen.dat}.
 *
 * <p>Keeps the last {@link #KEEP} sightings (login or logout) so
 * {@code /seen} can show recent history, not just one timestamp.
 */
public class SeenData extends SavedData {

    private static final String NAME = "seen";
    /** How many recent sightings to remember per player. */
    public static final int KEEP = 3;

    private static final Factory<SeenData> FACTORY =
        new Factory<>(SeenData::new, SeenData::load, null);

    private final Map<String, long[]> seen = new HashMap<>();

    public static SeenData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(FACTORY, NAME);
    }

    /** Recent timestamps newest-first (length 1..KEEP), or null if unknown. */
    public long[] get(String playerName) {
        return seen.get(playerName);
    }

    public void touch(String playerName) {
        long now = System.currentTimeMillis();
        long[] prev = seen.get(playerName);
        long[] next;
        if (prev == null) {
            next = new long[]{ now };
        } else {
            int len = Math.min(prev.length + 1, KEEP);
            next = new long[len];
            next[0] = now;
            System.arraycopy(prev, 0, next, 1, len - 1);
        }
        seen.put(playerName, next);
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        CompoundTag m = new CompoundTag();
        seen.forEach(m::putLongArray);
        tag.put(NAME, m);
        return tag;
    }

    private static SeenData load(CompoundTag tag, HolderLookup.Provider registries) {
        SeenData d = new SeenData();
        CompoundTag m = tag.getCompound(NAME);
        for (String k : m.getAllKeys()) {
            long[] arr = m.getLongArray(k);
            if (arr.length > KEEP) {
                arr = Arrays.copyOf(arr, KEEP);
            }
            d.seen.put(k, arr);
        }
        return d;
    }
}
