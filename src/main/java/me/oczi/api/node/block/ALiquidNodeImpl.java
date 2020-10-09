package me.oczi.api.node.block;

import me.oczi.api.LiquidType;
import me.oczi.api.Nodes;
import me.oczi.api.node.ANode;
import me.oczi.api.node.point.PointNode;
import me.oczi.util.BukkitParser;
import org.bukkit.World;
import org.bukkit.block.Block;

class ALiquidNodeImpl extends LiquidNodeImpl
    implements ALiquidNode {
    private final int h;
    private final int g;

    private final int f;

    public ALiquidNodeImpl(LiquidNode node, PointNode<LiquidNode> point) {
        this(node.getLiquidType(), node.getBlock(), point);
    }

    public ALiquidNodeImpl(Block block, PointNode<LiquidNode> point) {
        this(BukkitParser
            .checkedAsLiquid(block,
                "Invalid liquid type for block (not is a liquid?)"),
            block,
            point);
    }

    public ALiquidNodeImpl(LiquidType type, Block block, PointNode<LiquidNode> point) {
        super(type, block);
        if (point == null) {
            point = Nodes.emptyPointNode();
        }
        this.h = point.calculateGoalDistance(this);
        this.g = point.calculateStartDistance(this);
        this.f = h + g;
    }

    public ALiquidNodeImpl(LiquidType liquidType,
                           World world,
                           int x,
                           int y,
                           int z,
                           int h,
                           int g) {
        super(liquidType, world, x, y, z);
        this.h = h;
        this.g = g;
        this.f = h + g;
    }

    @Override
    public int getHeuristic() {
        return h;
    }

    @Override
    public int getG() {
        return g;
    }

    @Override
    public int getF() {
        return f;
    }

    @Override
    public int compareTo(ANode node) {
        return Integer
            .compare(getF(), node.getF());
    }


    @Override
    public String toString() {
        return "LiquidNode{{" + super.toString() + "}, h=" + h +
            ", g=" + g +
            '}';
    }
}
