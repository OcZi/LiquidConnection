package me.oczi.api.region;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Set;

/**
 * A region representation.
 */
public interface Region {

    boolean containsBlock(Block block);

    boolean isWithin(Location location);

    Set<Block> getBlocks();

    Set<Block> getBlocksBetweenLevel(int minY, int maxY);

    Location getFirstPosition();

    Location getSecondPosition();
}
