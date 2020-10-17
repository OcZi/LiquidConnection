package me.oczi.api.iterator;

import me.oczi.api.TaskState;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.node.block.LiquidNode;

import java.util.Iterator;

/**
 * A iterator to apply pathfinding between
 * two nodes of liquid.
 */
public interface LiquidIterator extends Iterator<LiquidNode> {

    /**
     * Get actual state of pathfinding.
     * @return State of pathfinding.
     */
    TaskState getState();

    /**
     * Get {@link CheckedSet} of iterator.
     * @return CheckedSet.
     */
    CheckedSet<LiquidNode> getCheckedSet();
}