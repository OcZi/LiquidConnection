package me.oczi.api.region;

import me.oczi.api.LiquidType;
import me.oczi.api.location.MutableLocation;
import me.oczi.util.BukkitParser;
import org.bukkit.Location;
import org.bukkit.block.Block;

public interface Regions {

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

    static Region newCuboid(LiquidType liquidType,
                            Location pos1,
                            Location pos2) {
        return new LiquidCuboidRegion(liquidType, pos1, pos2);
    }
}
