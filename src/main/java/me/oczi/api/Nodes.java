package me.oczi.api;

import me.oczi.api.node.Node;
import me.oczi.api.node.checkpoint.EmptyPointNode;
import me.oczi.api.node.goal.PointNode;

public interface Nodes {

    static <T extends Node> PointNode<T> emptyPointNode() {
        return new EmptyPointNode<>();
    }
}
