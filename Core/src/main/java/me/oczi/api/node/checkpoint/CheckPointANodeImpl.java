package me.oczi.api.node.checkpoint;

import com.google.common.collect.Lists;
import me.oczi.api.node.ANode;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class CheckPointANodeImpl<T extends ANode> implements CheckpointANode<T> {
    private final T delegate;
    private final Queue<T> checkpoints;

    @SafeVarargs
    public CheckPointANodeImpl(T delegate, T... checkpoints) {
        this(delegate, Lists.newArrayList(checkpoints));
    }

    public CheckPointANodeImpl(T delegate, Collection<T> checkpoints) {
        this.delegate = delegate;
        this.checkpoints = new PriorityQueue<>(checkpoints.size());
        addAndSort(checkpoints instanceof List
            ? (List<T>) checkpoints
            : Lists.newArrayList(checkpoints));
    }

    private void addAndSort(List<T> checkpoints) {
        checkpoints.sort(Comparable::compareTo);
        Collections.reverse(checkpoints);
        this.checkpoints.addAll(checkpoints);
    }

    @Override
    public int getX() {
        return delegate.getX();
    }

    @Override
    public int getY() {
        return delegate.getY();
    }

    @Override
    public int getZ() {
        return delegate.getZ();
    }

    @Override
    public int getHeuristic() {
        return delegate.getHeuristic();
    }

    @Override
    public int getG() {
        return delegate.getG();
    }

    @Override
    public int getF() {
        return delegate.getF();
    }

    @Override
    public boolean contains(ANode node) {
        return checkpoints.contains(node);
    }

    @Override
    public Queue<T> getNodes() {
        return checkpoints;
    }

    @Override
    public T getNearestNode() {
        return checkpoints.poll();
    }

    @Override
    public boolean isEmpty() {
        return checkpoints.isEmpty();
    }

    @Override
    public int compareTo(@NotNull ANode node) {
        return delegate.compareTo(node);
    }

    @Override
    public String toString() {
        return "CheckPointANodeImpl{" +
            "delegate=" + delegate +
            ", checkpoints=" + checkpoints +
            '}';
    }
}
