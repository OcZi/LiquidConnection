package me.oczi.exceptions;

import me.oczi.api.node.Node;
import me.oczi.util.CommonsBukkit;

public class UnreachableGoal extends RuntimeException {

    public UnreachableGoal(String errMessage, Throwable t) {
        super(errMessage, t);
    }

    public UnreachableGoal(String errMessage) {
        super(errMessage);
    }

    public UnreachableGoal(String errMessage, Node node, Node goal) {
        super(String.format("%s (Node: %s, Goal %s)",
            errMessage,
            CommonsBukkit.getNodeCoordinates(node),
            CommonsBukkit.getNodeCoordinates(goal)));
    }

    public UnreachableGoal(Throwable t) {
        super(t);
    }
}