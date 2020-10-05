package me.oczi.api.node.block;

import me.oczi.api.LiquidType;
import me.oczi.api.node.ANode;
import me.oczi.api.node.goal.BlockPointNode;
import me.oczi.util.BukkitParser;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public interface ALiquidNode
    extends ANode,
    LiquidNode {

    static ALiquidNode asNode(LiquidNode node, BlockPointNode<LiquidNode> goal) {
        return new ALiquidNodeImpl(node, goal);
    }

    static ALiquidNode asNode(Block block, BlockPointNode<LiquidNode> goal) {
        return new ALiquidNodeImpl(block, goal);
    }

    static ALiquidNode asNode(LiquidType liquidType, Block block, BlockPointNode<LiquidNode> goal) {
        return new ALiquidNodeImpl(liquidType, block, goal);
    }

    static Set<ALiquidNode> toSetNodes(Set<Block> blocks, BlockPointNode<LiquidNode> goal) {
        Set<ALiquidNode> nodes = new HashSet<>();
        for (Block block : blocks) {
            nodes.add(
                asNode(
                    BukkitParser.checkedAsLiquid(block, "A block is not a liquid."),
                    block,
                    goal));
        }
        return nodes;
    }
}
