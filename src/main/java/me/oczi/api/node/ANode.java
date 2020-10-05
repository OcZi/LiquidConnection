package me.oczi.api.node;

public interface ANode
    extends Node,
    Comparable<ANode> {

    int getHeuristic();

    int getG();

    int getF();
}
