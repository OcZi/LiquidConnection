package me.oczi.api.node.block;

import me.oczi.api.LiquidType;
import me.oczi.api.node.point.BlockPointNode;
import me.oczi.util.BukkitParser;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A {@link BlockNode} implementation for liquid blocks.
 */
public interface LiquidNode
    extends BlockNode {

    /**
     * Create a new liquidNode.
     *
     * @param liquidType Liquid type of node.
     * @param block      Block representation.
     * @return LiquidNode.
     */
    static LiquidNode newNode(LiquidType liquidType,
                              Block block) {
        return newNode(liquidType,
            block.getWorld(),
            block.getX(),
            block.getY(),
            block.getZ());
    }

    /**
     * Create a new LiquidNode.
     * @param block Block representation.
     * @return LiquidNode.
     */
    static LiquidNode asNode(Block block) {
        return new LiquidNodeImpl(block);
    }

    /**
     * Create a new liquidNode.
     *
     * @param liquidType Liquid type of node.
     * @param world      World.
     * @param x X axis.
     * @param y Y axis.
     * @param z Z axis.
     * @return LiquidNode.
     */
    static LiquidNode newNode(LiquidType liquidType,
                              World world,
                              int x, int y, int z) {
        return new LiquidNodeImpl(liquidType, world, x, y, z);
    }

    static Location toLocation(LiquidNode node) {
        return node.getBlock().getLocation();
    }

    /**
     * Convert all the blocks to LiquidNodes.
     * @param blocks blocks to nodes.
     * @return Array of LiquidNodes.
     */
    static LiquidNode[] toNodes(List<Block> blocks) {
        LiquidNode[] nodes = new LiquidNode[blocks.size()];
        for (int i = 0; i < blocks.size(); i++) {
            nodes[i] = asNode(blocks.get(i));
        }
        return nodes;
    }

    /**
     * Convert all the blocks into LiquidNodes.
     * @param blocks Blocks to LiquidNodes.
     * @return Set of liquidNodes.
     */
    static Set<LiquidNode> toSetNodes(Set<Block> blocks) {
        Set<LiquidNode> nodes = new HashSet<>();
        for (Block block : blocks) {
            nodes.add(
                newNode(
                    BukkitParser.checkedAsLiquid(block, "A block is not a liquid."),
                    block));
        }
        return nodes;
    }

    /**
     * Get liquid type of Node.
     * @return Liquid type.
     */
    LiquidType getLiquidType();

    /**
     * Convert LiquidNode to A* LiquidNode.
     * @param pointNode Point of nodes.
     * @return {@link ALiquidNode}.
     */
    ALiquidNode toAStarNode(BlockPointNode<LiquidNode> pointNode);
}
