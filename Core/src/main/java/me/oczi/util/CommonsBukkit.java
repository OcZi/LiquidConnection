package me.oczi.util;

import me.oczi.api.location.RegionLocation;
import me.oczi.api.node.Node;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public interface CommonsBukkit {

    static Set<Block> waterRegionOf(Location pos1,
                                    Location pos2) {
        return blockRegionOf(pos1, pos2,
            block -> block.getType() == Material.WATER ||
                isActiveWaterLogged(block));
    }

    static Set<Block> specificBlockRegionOf(Location pos1,
                                            Location pos2,
                                            @Nullable Material filter) {
        return blockRegionOf(pos1, pos2,
            block -> filter == null || block.getType() == filter);
    }


    static Set<Block> blockRegionOf(Location pos1,
                                    Location pos2,
                                    Predicate<Block> predicate) {
        RegionLocation regionLocation = new RegionLocation(pos1, pos2);
        if (!regionLocation.isValid()) {
            return Collections.emptySet();
        }
        return iterateBlocks(
            regionLocation,
            predicate);
    }

    static Set<Block> iterateBlocks(RegionLocation region,
                                    Predicate<Block> predicate) {
        Set<Block> blocks = new HashSet<>();
        for (int x = region.getMinX(); x < region.getMaxX(); x++) {
            for (int y = region.getMinY(); y < region.getMaxY(); y++) {
                for (int z = region.getMinZ(); z < region.getMaxZ(); z++) {
                    Location location = new Location(
                        region.getWorld(), x, y, z);
                    Block block = location.getBlock();
                    if (predicate.test(block)) {
                        blocks.add(block);
                    }
                }
            }
        }
        return blocks;
    }

    static Set<Block> getBlocksOf(Set<Block> blocks,
                                  Material material) {
        blocks.removeIf(
            block -> block.getType() != material);
        return blocks;
    }


    static boolean isCoordinateEquals(Node node, Location location) {
        return
            node.getX() == location.getBlockX() &&
                node.getY() == location.getBlockY() &&
                node.getZ() == location.getBlockZ();
    }

    static boolean isLiquid(Block... blocks) {
        for (Block block : blocks) {
            if (!block.isLiquid()) {
                return false;
            }
        }
        return true;
    }

    static boolean isActiveWaterLogged(Block block) {
        BlockData data = block.getBlockData();
        return data instanceof Waterlogged &&
            ((Waterlogged) data).isWaterlogged();
    }
}
