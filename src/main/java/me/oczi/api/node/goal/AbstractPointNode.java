package me.oczi.api.node.goal;

import me.oczi.api.node.Node;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractPointNode<T extends Node>
    implements PointNode<T> {
    protected final T start;
    protected final T goal;

    public AbstractPointNode(T start, T goal) {
        checkNotNull(start, "Start node is null.");
        checkNotNull(goal, "Goal node is null.");
        this.start = start;
        this.goal = goal;
    }

    @Override
    public abstract int calculateStartDistance(T node);

    @Override
    public abstract int calculateGoalDistance(T node);

    @Override
    public T getStart() {
        return start;
    }

    @Override
    public T getGoal() {
        return goal;
    }

    @Override
    public int getX() {
        return goal.getX();
    }

    @Override
    public int getY() {
        return goal.getY();
    }

    @Override
    public int getZ() {
        return goal.getZ();
    }

    @Override
    public String toString() {
        return "BlockPointNodeImpl{" +
            "start=" + start +
            ", goal=" + goal +
            '}';
    }
}
