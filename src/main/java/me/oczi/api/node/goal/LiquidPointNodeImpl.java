package me.oczi.api.node.goal;

import me.oczi.api.LiquidType;
import me.oczi.api.node.block.LiquidNode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import static com.google.common.base.Preconditions.checkArgument;
import static me.oczi.util.LiquidPreconditions.checkLevel;

class LiquidPointNodeImpl<T extends LiquidNode>
    extends AbstractPointNode<T>
    implements LiquidPointNode<T> {
    private final LiquidType liquidType;

    public LiquidPointNodeImpl(T start, T goal) {
        super(start, goal);
        checkArgument(LiquidType.isValid(
            start.getLiquidType(), goal.getLiquidType()),
            "Block type of points is not liquid.");
        checkArgument(start.getLiquidType() == goal.getLiquidType(),
            "Start and goal have different type of liquid.");
        this.liquidType = start.getLiquidType();;
    }

    @Override
    public int calculateStartDistance(T node) {
        return calculateDistance(node, start);
    }

    @Override
    public int calculateGoalDistance(T node) {
        // Goal cannot be in a minor level Y of LiquidNode.
        // That make impossible to be reached due to liquid physics.
        checkLevel(node, goal);
        return calculateDistance(node, goal);
    }

    private int calculateDistance(T node1, T node2) {
        Location location = node1.getBlockLocation();
        return (int) location.distanceSquared(
            node2.getBlockLocation());
    }

    @Override
    public LiquidType getLiquidType() {
        return liquidType;
    }

    @Override
    public Material getType() {
        return start.getType();
    }

    @Override
    public Block getBlock() {
        return start.getBlock();
    }

    @Override
    public String toString() {
        return "LiquidPointNodeImpl{liquidType=" + liquidType + "}";
    }
}
