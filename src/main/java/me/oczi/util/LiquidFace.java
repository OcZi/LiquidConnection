package me.oczi.util;

import me.oczi.api.node.Node;
import me.oczi.api.node.block.BlockNode;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public enum LiquidFace {
    NORTH(BlockFace.NORTH),
    EAST(BlockFace.EAST),
    SOUTH( BlockFace.SOUTH),
    WEST(BlockFace.WEST),
    UP(BlockFace.UP);
    //DOWN(BlockFace.DOWN);

    private final BlockFace blockFace;

    LiquidFace(BlockFace legacyFace) {
        this.blockFace = legacyFace;
    }

    private static final EnumSet<LiquidFace> faces =
        EnumSet.allOf(LiquidFace.class);

    public static EnumSet<LiquidFace> faces() {
        return faces;
    }

    public static LiquidFace oppositeFace(LiquidFace face) {
        switch (face) {
            case NORTH:
                return LiquidFace.SOUTH;
            case SOUTH:
                return LiquidFace.NORTH;
            case EAST:
                return LiquidFace.WEST;
            case WEST:
                return LiquidFace.EAST;
            case UP:
            default:
                return null;
        }
    }

    public static LiquidFace toLiquidFace(@Nullable BlockFace face) {
        if (face == null) {
            return null;
        }
        switch (face) {
            case NORTH_NORTH_EAST:
            case NORTH_NORTH_WEST:
            case NORTH_EAST:
            case NORTH_WEST:
            case NORTH:
                return LiquidFace.NORTH;
            case SOUTH_SOUTH_EAST:
            case SOUTH_SOUTH_WEST:
            case SOUTH_EAST:
            case SOUTH_WEST:
            case SOUTH:
                return LiquidFace.SOUTH;
            case EAST_NORTH_EAST:
            case EAST_SOUTH_EAST:
            case EAST:
                return LiquidFace.EAST;
            case WEST_SOUTH_WEST:
            case WEST_NORTH_WEST:
            case WEST:
                return LiquidFace.WEST;
            case UP:
                return LiquidFace.UP;
            case SELF:
            case DOWN:
            default:
                return null;
        }
    }

    public LiquidFace oppositeFace() {
        return oppositeFace(this);
    }

    public static boolean anyAdjacent(BlockNode node, Node nodeEquals) {
        for (LiquidFace face : faces()) {
            if (face.isAdjacent(node, nodeEquals)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdjacent(BlockNode node, Node nodeEquals) {
        return CommonsBukkit.isCoordsEquals(
            node.getBlock().getRelative(blockFace),
            nodeEquals);
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }
}
