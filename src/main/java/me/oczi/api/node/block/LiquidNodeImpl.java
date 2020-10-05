package me.oczi.api.node.block;

import me.oczi.api.LiquidType;
import me.oczi.api.node.AbstractNode;
import me.oczi.api.node.goal.BlockPointNode;
import me.oczi.util.BukkitParser;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import static com.google.common.base.Preconditions.checkArgument;

public class LiquidNodeImpl
    extends AbstractNode implements LiquidNode {
    private final Block block;
    private final LiquidType type;

    public LiquidNodeImpl(Block block) {
        this(BukkitParser.asLiquid(block),
            block.getWorld(),
            block.getX(),
            block.getY(),
            block.getZ());
    }

    public LiquidNodeImpl(LiquidType liquidType,
                          Block block) {
        this(liquidType,
            block.getWorld(),
            block.getX(), block.getY(), block.getZ());
    }

    public LiquidNodeImpl(LiquidType liquidType,
                          World world,
                          int x,
                          int y,
                          int z) {
        super(x, y, z);
        this.type = liquidType;
        checkArgument(type != LiquidType.NONE,
            "Invalid liquid type for block (not is a liquid?)");
        this.block = world.getBlockAt(x, y, z);
    }

    @Override
    public Material getType() {
        return block.getType();
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public Location getBlockLocation() {
        return block.getLocation();
    }

    @Override
    public LiquidType getLiquidType() {
        return type;
    }

    @Override
    public ALiquidNode toAStarNode(BlockPointNode<LiquidNode> pointNode) {
        return ALiquidNode.asNode(this, pointNode);
    }

    @Override
    public String toString() {
        return "LiquidNodeImpl{block=" + block +
            ", type=" + type +
            '}';
    }
}
