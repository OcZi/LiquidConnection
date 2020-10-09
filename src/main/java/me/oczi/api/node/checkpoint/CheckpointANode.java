package me.oczi.api.node.checkpoint;

import me.oczi.api.node.ANode;

import java.util.Collection;
import java.util.Queue;

/**
 * A* Node that contains nodes ordered by their F value.
 * Used as checkpoints in pathfinding.
 * @param <T> Type of A* Node.
 */
public interface CheckpointANode<T extends ANode> extends ANode {

    /**
     * Create a new Checkpoint.
     * @param delegate A* Node delegate.
     * @param nodes A* Nodes to collect..
     * @param <T> Type of nodes.
     * @return Checkpoint.
     */
    @SafeVarargs
    static <T extends ANode> CheckpointANode<T> newCheckpoint(T delegate,
                                                              T... nodes) {
        return new CheckPointANodeImpl<>(delegate, nodes);
    }

    /**
     * Create a new Checkpoint.
     * @param delegate A* Node delegate.
     * @param nodes A* Nodes to collect..
     * @param <T> Type of nodes.
     * @return Checkpoint.
     */
    static <T extends ANode> CheckpointANode<T> newCheckpoint(T delegate,
                                                              Collection<T> nodes) {
        return new CheckPointANodeImpl<>(delegate, nodes);
    }

    /**
     * Check checkpoints contains a node.
     * @param node Node to check.
     * @return contains node or not.
     */
    boolean contains(ANode node);

    /**
     * Get nodes of checkpoint.
     * @return Nodes of checkpoint.
     */
    Queue<T> getNodes();

    /**
     * Get the node that have the mayor value of F.
     * @return Node.
     */
    T getNearestNode();

    /**
     * Check is empty.
     * @return is empty.
     */
    boolean isEmpty();
}
