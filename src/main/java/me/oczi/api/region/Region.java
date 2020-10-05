package me.oczi.api.region;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;
import java.util.Set;

public interface Region {

    boolean containsBlock(Block block);

    boolean isWithin(Location location);

    Set<Block> getBlocks();

    Set<Block> getBlocksBetweenLevel(int minY, int maxY);

    Location getFirstPosition();

    Location getSecondPosition();
}
