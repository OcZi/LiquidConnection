package me.oczi.util;

import me.oczi.api.node.Node;

public interface Maths {

    static int manhattanDistance3D(Node node1, Node node2) {
        int x = Math.abs(node1.getX() - node2.getX());
        int y = Math.abs(node1.getY() - node2.getY());
        int z = Math.abs(node1.getZ() - node2.getZ());
        return x + y + z;
    }

    static int manhattanDistance2D(Node node1, Node node2) {
        int x = Math.abs(node1.getX() - node2.getX());
        int z = Math.abs(node1.getZ() - node2.getZ());
        return x + z;
    }
}
