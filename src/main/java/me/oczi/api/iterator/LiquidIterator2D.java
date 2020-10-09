package me.oczi.api.iterator;

import me.oczi.api.TaskState;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.point.LiquidPointNode;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: this implementation, someday...
 */
public class LiquidIterator2D extends AbstractLiquidIterator {

    public LiquidIterator2D(LiquidPointNode<LiquidNode> point,
                            CheckedSet<ALiquidNode> checkedSet,
                            @Nullable BlockFace ignoreFace) {
        super(point, checkedSet, ignoreFace);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public LiquidNode next() {
        return null;
    }

    @Override
    public TaskState getState() {
        return null;
    }

    @Override
    public CheckedSet<ALiquidNode> getCheckedSet() {
        return null;
    }
}
