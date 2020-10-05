package me.oczi.api.node.goal;

import me.oczi.api.node.Node;

public interface PointNode<T extends Node> extends Node {

    int calculateStartDistance(T node);

    int calculateGoalDistance(T node);

    T getStart();

    T getGoal();
}
