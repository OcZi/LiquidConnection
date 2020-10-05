package me.oczi.api.iterator;

import com.google.common.collect.EvictingQueue;
import me.oczi.api.TaskState;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.checkpoint.CheckpointANode;
import me.oczi.api.node.goal.LiquidPointNode;
import me.oczi.util.CommonsBukkit;
import me.oczi.util.LiquidFace;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;

public abstract class AbstractLiquidIterator implements LiquidIterator {
    protected final LiquidPointNode<LiquidNode> point;

    protected ALiquidNode currentNode;

    protected LiquidFace ignoreFace;
    protected final int max;
    protected int tries = 0;

    protected TaskState state;

    protected Queue<CheckpointANode<ALiquidNode>> checkpoints =
        EvictingQueue.create(5);
    protected CheckpointANode<ALiquidNode> latestCheckpoint;

    protected final CheckedSet<ALiquidNode> checkedSet;

    public AbstractLiquidIterator(LiquidPointNode<LiquidNode> point,
                                  CheckedSet<ALiquidNode> checkedSet,
                                  @Nullable BlockFace ignoreFace) {
        this.point = point;
        this.currentNode = ALiquidNode
            .asNode(point.getStart(), point);
        this.checkedSet = checkedSet;
        this.ignoreFace = LiquidFace.toLiquidFace(ignoreFace);

        LiquidNode goal = point.getGoal();
        if (CommonsBukkit.isCoordsEquals(currentNode, goal) ||
            CommonsBukkit.isAdjacent(currentNode, goal, ignoreFace)) {
            this.max = 0;
            this.state = TaskState.SUCCESSFULLY;
            return;
        }
        // Set the maximum iterations with the Node's F value
        // multiplied by 1.5 to avoid possible infinite loops.
        this.max = (int) Math.round(currentNode.getF() * 1.5);
        this.state = TaskState.UNDEFINED;
    }

    @Override
    public abstract boolean hasNext();

    @Override
    public abstract LiquidNode next();

    @Override
    public TaskState getState() {
        return state;
    }

    @Override
    public CheckedSet<ALiquidNode> getCheckedSet() {
        return checkedSet;
    }
}