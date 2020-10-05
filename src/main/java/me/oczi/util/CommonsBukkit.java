package me.oczi.util;

import com.google.common.collect.Sets;
import me.oczi.api.LiquidType;
import me.oczi.api.location.RegionLocation;
import me.oczi.api.node.Node;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.BlockNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.goal.BlockPointNode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public interface CommonsBukkit {

    static Set<ALiquidNode> getAdjacentLiquidNodes(@NotNull LiquidNode node,
                                                   @NotNull BlockPointNode<LiquidNode> pointNode,
                                                   @Nullable Collection<LiquidFace> ignoredFace) {
        Set<ALiquidNode> nodes = new HashSet<>();
        Block block = node.getBlock();
        for (LiquidFace face : LiquidFace.faces()) {
            if (isEqualsToFace(face, ignoredFace)) {
                continue;
            }
            Block relative = block.getRelative(face.getBlockFace());
            LiquidType relativeType = BukkitParser
                .uncheckedAsLiquid(relative);
            if (relativeType != LiquidType.NONE &&
                relativeType == node.getLiquidType()) {
                nodes.add(ALiquidNode
                    .asNode(relativeType, relative, pointNode));
            }
        }
        return nodes;
    }

    static void filterBlockNode(List<? extends BlockNode> blocks,
                                Material material) {
        blocks.removeIf(block ->
            block.getBlock().getType() != material);
    }

    static Set<Block> filterLevelNode(Set<Block> blocks,
                                      int minY,
                                      int maxY) {
        Set<Block> result = Sets.newHashSet(blocks);
        Predicate<Block> predicate = minY == maxY
            ? b -> b.getY() == minY
            : b -> b.getY() >= minY || b.getY() <= maxY;
        result.removeIf(
            b -> !predicate.test(b));
        return result;
    }


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

    static boolean isEqualsToFace(LiquidFace face,
                                  @Nullable Collection<LiquidFace> faces) {
        if (Commons.isNullOrEmpty(faces)) {
            return false;
        }
        for (LiquidFace blockFace : faces) {
            if (face == blockFace) {
                return true;
            }
        }
        return false;
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

    static boolean isAdjacent(BlockNode blockNode,
                              Node node,
                              BlockFace ignore) {
        for (BlockFace face : BlockFace.values()) {
            if (face == ignore) {
                continue;
            }
            Block relative = blockNode.getBlock().getRelative(face);
            if (isCoordsEquals(relative, node)) {
                return true;
            }
        }
        return false;
    }

    static String getNodeCoordinates(Node node) {
        return "x: " + node.getX() +
            ", y: " + node.getY() +
            ", z: " + node.getZ();
    }

    static boolean isCoordsEquals(Node node1, Node node2) {
        return node1.getX() == node2.getX() &&
            node1.getY() == node2.getY() &&
            node1.getZ() == node2.getZ();
    }

    static boolean isCoordsEquals(Block block, Node node) {
        return block.getX() == node.getX() &&
            block.getY() == node.getY() &&
            block.getZ() == node.getZ();
    }

    static boolean isActiveWaterLogged(Block block) {
        BlockData data = block.getBlockData();
        return data instanceof Waterlogged &&
            ((Waterlogged) data).isWaterlogged();
    }

    static boolean isSameYLevel(Node node1, Node node2) {
        return node1.getY() == node2.getY();
    }

    static void printCoords(Node node) {
        System.out.println(CommonsBukkit.getNodeCoordinates(node));
    }
}
