package me.oczi.api.node;

/**
 * Node for A* pathfinding.
 */
public interface ANode
    extends Node,
    Comparable<ANode> {

    /**
     * Get heuristic of Node.
     * @return Heuristic.
     */
    int getHeuristic();

    /**
     * Get G value of Node.
     * @return G.
     */
    int getG();

    /**
     * Get F value of Node.
     * @return F.
     */
    int getF();
}
