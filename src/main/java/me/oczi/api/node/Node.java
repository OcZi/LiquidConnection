package me.oczi.api.node;

/**
 * Basic node representation.
 */
public interface Node {

    /**
     * Create a generic Node.
     * @param x X axis.
     * @param y Y axis.
     * @param z Z axis.
     * @return Generic Node.
     */
    static Node genericNode(int x, int y, int z) {
        return new AbstractNode(x, y, z) {
            @Override
            public boolean equals(Object o) {
                return super.equals(o);
            }
        };
    }

    /**
     * Get X axis of Node.
     * @return X axis.
     */
    int getX();

    /**
     * Get Y axis of Node.
     * @return Y axis.
     */
    int getY();

    /**
     * Get Z axis of Node.
     * @return Z axis.
     */
    int getZ();
}
