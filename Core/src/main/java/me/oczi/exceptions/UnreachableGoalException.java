package me.oczi.exceptions;

import me.oczi.api.node.Node;
import me.oczi.util.CommonsNode;

public class UnreachableGoalException extends RuntimeException {

    public UnreachableGoalException(String errMessage, Throwable t) {
        super(errMessage, t);
    }

    public UnreachableGoalException(String errMessage) {
        super(errMessage);
    }

    public UnreachableGoalException(String errMessage, Node node, Node goal) {
        super(String.format("%s (Node: %s, Goal %s)",
            errMessage,
            CommonsNode.getNodeCoordinates(node),
            CommonsNode.getNodeCoordinates(goal)));
    }

    public UnreachableGoalException(Throwable t) {
        super(t);
    }
}