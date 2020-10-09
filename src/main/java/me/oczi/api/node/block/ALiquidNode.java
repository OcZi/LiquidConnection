package me.oczi.api.node.block;

import me.oczi.api.LiquidType;
import me.oczi.api.node.ANode;
import me.oczi.api.node.point.BlockPointNode;
import me.oczi.util.BukkitParser;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

/**
 * A {@link LiquidNode} implementations with {@link ANode}.
 */
public interface ALiquidNode
    extends ANode,
    LiquidNode {

    /**
     * Create a new ALiquidNode.
     * @param node LiquidNode delegate.
     * @param point Point of node.
     * @return AliquidNode.
     */
    static ALiquidNode asNode(LiquidNode node, BlockPointNode<LiquidNode> point) {
        return new ALiquidNodeImpl(node, point);
    }

    /**
     * Create a new ALiquidNode.
     * @param block Block representation.
     * @param point Point of node.
     * @return AliquidNode.
     */
    static ALiquidNode asNode(Block block, BlockPointNode<LiquidNode> point) {
        return new ALiquidNodeImpl(block, point);
    }

    /**
     * Create a new ALiquidNode.
     * @param liquidType Liquid type of ALiquidNode.
     * @param block Block representation.
     * @param point Point of node.
     * @return AliquidNode.
     */
    static ALiquidNode asNode(LiquidType liquidType, Block block, BlockPointNode<LiquidNode> point) {
        return new ALiquidNodeImpl(liquidType, block, point);
    }

    /**
     * Convert all the blocks into ALiquidNodes.
     * @param blocks Blocks to AliquidNodes.
     * @param point Point of node.
     * @return Set of AliquidNodes.
     */
    static Set<ALiquidNode> toSetNodes(Set<Block> blocks, BlockPointNode<LiquidNode> point) {
        Set<ALiquidNode> nodes = new HashSet<>();
        for (Block block : blocks) {
            nodes.add(
                asNode(
                    BukkitParser.checkedAsLiquid(block, "A block is not a liquid."),
                    block,
                    point));
        }
        return nodes;
    }
}
