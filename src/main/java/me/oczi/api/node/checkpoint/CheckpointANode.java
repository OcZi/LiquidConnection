package me.oczi.api.node.checkpoint;

import me.oczi.api.node.ANode;

import java.util.Collection;
import java.util.Queue;

public interface CheckpointANode<T extends ANode> extends ANode {

    @SafeVarargs
    static <T extends ANode> CheckpointANode<T> newCheckpoint(T delegate,
                                                              T... checkpoints) {
        return new CheckPointANodeImpl<>(delegate, checkpoints);
    }

    static <T extends ANode> CheckpointANode<T> newCheckpoint(T delegate,
                                                              Collection<T> checkpoints) {
        return new CheckPointANodeImpl<>(delegate, checkpoints);
    }

    boolean contains(ANode node);

    Queue<T> getNodes();

    T getNearestNode();

    boolean isEmpty();
}
