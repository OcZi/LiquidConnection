package me.oczi.util;

import me.oczi.api.node.Node;
import me.oczi.exceptions.UnreachableGoal;

public interface LiquidPreconditions {

    static void checkLevel(Node node1, Node node2) {
        if (node1.getY() > node2.getY()) {
            throw new UnreachableGoal(
                "Goal is unreachable for this node",
                node1, node2);
        }
    }
}
