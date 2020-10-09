package me.oczi.api.region;

import me.oczi.api.LiquidType;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.util.CommonsBukkit;
import me.oczi.util.CommonsNode;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public class LiquidCuboidRegion implements Region {
    private final Location pos1;
    private final Location pos2;

    private final Set<Block> blocks;

    public LiquidCuboidRegion(LiquidType type,
                              Location pos1,
                              Location pos2) {
        checkArgument(type != LiquidType.NONE,
            "Liquid type is invalid (Bad parse of material?)");
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.blocks = type == LiquidType.WATER
            ? CommonsBukkit.waterRegionOf(
            pos1, pos2)
            : CommonsBukkit.specificBlockRegionOf(
            pos1, pos2, type.getMaterial());
    }

    @Override
    public boolean containsBlock(Block block) {
        return blocks.contains(block);
    }

    @Override
    public boolean isWithin(Location location) {
        for (Block block : blocks) {
            Location location1 = block.getLocation();
            if (location.equals(location1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWithin(LiquidNode node) {
        for (Block block : blocks) {
            Location location = block.getLocation();
            if (CommonsBukkit.isCoordinateEquals(node, location)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Block> getBlocks() {
        return blocks;
    }

    @Override
    public Set<Block> getBlocksBetweenLevel(int minY, int maxY) {
        return CommonsNode.filterLevelNode(blocks, minY, maxY);
    }

    @Override
    public Location getFirstPosition() {
        return pos1;
    }

    @Override
    public Location getSecondPosition() {
        return pos2;
    }
}
