package me.oczi.api;

import me.oczi.api.node.Node;
import me.oczi.api.node.checkpoint.EmptyPointNode;
import me.oczi.api.node.point.PointNode;

/**
 * Nodes utils.
 */
public interface Nodes {

    /**
     * Get a empty point node.
     * @param <T> Type of node.
     * @return Empty PointNode.
     */
    static <T extends Node> PointNode<T> emptyPointNode() {
        return new EmptyPointNode<>();
    }
}
