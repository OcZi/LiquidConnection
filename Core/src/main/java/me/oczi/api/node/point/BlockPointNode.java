package me.oczi.api.node.point;

import me.oczi.api.node.block.BlockNode;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * A {@link PointNode} implementation with {@link BlockNode}.
 * @param <T> Type of block node.
 */
public interface BlockPointNode<T extends BlockNode>
    extends PointNode<T> {

    /**
     * Get material of goal point.
     * @return Goal Material.
     */
    Material getStartType();

    /**
     * Get block of goal point.
     * @return Goal Block.
     */
    Block getStartBlock();

    /**
     * Get material of start point.
     * @return Start Material.
     */
    Material getGoalType();

    /**
     * Get block of start point.
     * @return Start Block.
     */
    Block getGoalBlock();
}
