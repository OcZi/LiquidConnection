package me.oczi.api.node.point;

import me.oczi.api.LiquidType;
import me.oczi.api.node.block.LiquidNode;

/**
 * A {@link PointNode} implementation with {@link LiquidNode}.
 * @param <T> Type of liquid node.
 */
public interface LiquidPointNode<T extends LiquidNode>
    extends BlockPointNode<T> {

    /**
     * Create a new LiquidPointNode.
     * @param start Start of point.
     * @param goal Goal of point.
     * @param <T> Type of liquid node
     * @return LiquidPointNode
     */
    static <T extends LiquidNode> LiquidPointNode<T> pointOf(T start, T goal) {
        return new LiquidPointNodeImpl<>(start, goal);
    }

    /**
     * Get liquid type of point.
     * @return Liquid type of point.
     */
    LiquidType getLiquidType();
}
