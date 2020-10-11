package me.oczi.api.location;

import org.bukkit.Location;
import org.bukkit.World;

class MutableLocationImpl implements MutableLocation {
    private World world;
    private int x;
    private int y;
    private int z;

    public MutableLocationImpl(Location location) {
        this.world = location.getWorld();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    @Override
    public MutableLocation addAll(int sum) {
        this.x += sum;
        this.y += sum;
        this.z += sum;
        return this;
    }

    @Override
    public MutableLocation removeAll(int sub) {
        this.x -= sub;
        this.y -= sub;
        this.z -= sub;
        return this;
    }

    @Override
    public MutableLocation world(World world) {
        this.world = world;
        return this;
    }

    @Override
    public MutableLocation addX(int x) {
        this.x += x;
        return this;
    }

    @Override
    public MutableLocation removeX(int x) {
        this.x -= x;
        return this;
    }

    @Override
    public MutableLocation addY(int y) {
        this.y += y;
        return this;
    }

    @Override
    public MutableLocation removeY(int y) {
        this.y -= y;
        return this;
    }

    @Override
    public MutableLocation addZ(int z) {
        this.z += z;
        return this;
    }

    @Override
    public MutableLocation removeZ(int z) {
        this.z -= z;
        return this;
    }

    @Override
    public Location apply() {
        return new Location(world, x, y, z);
    }
}
