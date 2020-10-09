package me.oczi.api.node.block;

import me.oczi.api.node.Node;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Node that represents a {@link Block}.
 */
public interface BlockNode extends Node {

    /**
     * Get material of block.
     * @return Material of block.
     */
    Material getType();

    /**
     * Get block of node.
     * @return Block of node.
     */
    Block getBlock();

    /**
     * Get location of block.
     * @return location of block.
     */
    Location getBlockLocation();
}
