package me.oczi.api.region;

import me.oczi.api.LiquidType;
import me.oczi.api.location.MutableLocation;
import me.oczi.util.BukkitParser;
import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 * Region utils.
 */
public interface Regions {

    /**
     * Create a new Cuboid with a specific radius.
     * PD: will never get blocks below of block center.
     * @param blockCenter Block center to calculate radius.
     * @param radius Amount of radius.
     * @return Region.
     */
    static Region newCuboidWithRadius(Block blockCenter, int radius) {
        Location center = blockCenter.getLocation();
        Location pos1 = MutableLocation
            .mutateLocation(center)
            .addAll(radius)
            .apply();
        Location pos2 = MutableLocation
            .mutateLocation(center)
            .removeX(radius)
            .removeZ(radius)
            .apply();
        return newCuboid(
            BukkitParser.checkedAsLiquid(
                blockCenter, "Block center is not a liquid"),
            pos1,
            pos2);
    }

    /**
     * Create a new Region Cuboid.
     * @param liquidType Liquid Type of cuboid.
     * @param pos1 Position 1 of cuboid.
     * @param pos2 Position 2 of cuboid.
     * @return Region.
     */
    static Region newCuboid(LiquidType liquidType,
                            Location pos1,
                            Location pos2) {
        return new LiquidCuboidRegion(liquidType, pos1, pos2);
    }
}
