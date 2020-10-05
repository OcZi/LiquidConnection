package me.oczi.api.location;

import org.bukkit.Location;
import org.bukkit.World;

public interface MutableLocation {

    static MutableLocation  mutateLocation(Location location) {
        return new MutableLocationImpl(location);
    }

    MutableLocation addAll(int sum);

    MutableLocation removeAll(int sub);

    MutableLocation world(World world);

    MutableLocation addX(int x);

    MutableLocation removeX(int x);

    MutableLocation addY(int y);

    MutableLocation removeY(int y);

    MutableLocation addZ(int z);

    MutableLocation removeZ(int z);

    Location apply();
}
