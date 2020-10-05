package me.oczi.api.node;

public interface Node {

    static Node genericNode(int x, int y, int z) {
        return new AbstractNode(x, y, z) {
            @Override
            public boolean equals(Object o) {
                return super.equals(o);
            }
        };
    }

    int getX();

    int getY();

    int getZ();
}
