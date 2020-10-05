package me.oczi.api.location;

import org.bukkit.Location;
import org.bukkit.World;

public class RegionLocation {
    private final World world;

    private final int maxX;
    private final int maxY;
    private final int maxZ;

    private final int minX;
    private final int minY;
    private final int minZ;

    public RegionLocation(Location pos1, Location pos2) {
        this.world = pos1.getWorld() == pos1.getWorld()
            ? pos1.getWorld() : null;
        this.minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        this.minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        this.minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

        this.maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        this.maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        this.maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());
    }

    public boolean isValid() {
        return world != null;
    }

    public World getWorld() {
        return world;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMinZ() {
        return minZ;
    }
}
