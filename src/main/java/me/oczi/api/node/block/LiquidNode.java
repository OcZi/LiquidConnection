package me.oczi.api.node.block;

import me.oczi.api.LiquidType;
import me.oczi.api.node.goal.BlockPointNode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public interface LiquidNode
    extends BlockNode {

    static LiquidNode newNode(LiquidType liquidType,
                              Block block) {
        return newNode(liquidType,
            block.getWorld(),
            block.getX(),
            block.getY(),
            block.getZ());
    }

    static LiquidNode newNode(LiquidType liquidType,
                               World world,
                               int x, int y, int z) {
        return new LiquidNodeImpl(liquidType, world, x, y, z);
    }

    static LiquidNode asNode(Block block) {
        return new LiquidNodeImpl(block);
    }

    static Location toLocation(LiquidNode node) {
        return node.getBlock().getLocation();
    }

    static LiquidNode[] toNodes(List<Block> blocks) {
        LiquidNode[] nodes = new LiquidNode[blocks.size()];
        for (int i = 0; i < blocks.size(); i++) {
            nodes[i] = asNode(blocks.get(i));
        }
        return nodes;
    }

    static List<LiquidNode> toListNodes(List<Block> blocks) {
        List<LiquidNode> nodes = new ArrayList<>();
        for (Block block : blocks) {
            nodes.add(asNode(block));
        }
        return nodes;
    }

    LiquidType getLiquidType();

    ALiquidNode toAStarNode(BlockPointNode<LiquidNode> pointNode);
}
