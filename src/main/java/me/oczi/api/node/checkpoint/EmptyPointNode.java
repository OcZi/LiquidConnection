package me.oczi.api.node.checkpoint;

import me.oczi.api.node.Node;
import me.oczi.api.node.goal.PointNode;

public class EmptyPointNode<T extends Node> implements PointNode<T> {

    @Override
    public int calculateStartDistance(T node) {
        return 0;
    }

    @Override
    public int calculateGoalDistance(T node) {
        return 0;
    }

    @Override
    public T getStart() {
        return null;
    }

    @Override
    public T getGoal() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }
}
