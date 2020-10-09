package me.oczi.api.node.point;

import me.oczi.api.node.Node;

/**
 * A pair of start and goal nodes.
 * Used to calculate distance between them.
 * @param <T> Type of node.
 */
public interface PointNode<T extends Node> extends Node {

    /**
     * Calculate distance between node parameter and the start node.
     * @param node Node to calculate distance with start.
     * @return Distance between node and start.
     */
    int calculateStartDistance(T node);

    /**
     * Calculate distance between node parameter and the goal node.
     * @param node Node to calculate distance with goal.
     * @return Distance between node and goal.
     */
    int calculateGoalDistance(T node);

    /**
     * Get start node.
     * @return start node.
     */
    T getStart();

    /**
     * Get goal node.
     * @return goal node.
     */
    T getGoal();
}
