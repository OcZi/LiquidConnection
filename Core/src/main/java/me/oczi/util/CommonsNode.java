package me.oczi.util;

import com.google.common.collect.Sets;
import me.oczi.api.LiquidType;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.node.Node;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.BlockNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.point.BlockPointNode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public interface CommonsNode {

    static Set<ALiquidNode> getAdjacentLiquidNodes(@NotNull LiquidNode node,
                                                   @NotNull CheckedSet<LiquidNode> checkedSet,
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
            if (LiquidType.isValid(relativeType) &&
                relativeType == node.getLiquidType()) {
                LiquidNode liquidNode = LiquidNode
                    .newNode(relativeType, relative);
                if (checkedSet.isWhiteSetted(liquidNode)) {
                    nodes.add(liquidNode.toAStarNode(pointNode));
                }
            }
        }
        return nodes;
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

    static boolean isSameYLevel(Node node1, Node node2) {
        return node1.getY() == node2.getY();
    }
}
