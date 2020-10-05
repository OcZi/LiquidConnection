package me.oczi.api.node;

import me.oczi.util.CommonsBukkit;

import java.util.Objects;

public abstract class AbstractNode
    implements Node {
    protected final int x;
    protected final int y;
    protected final int z;

    public AbstractNode(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        return CommonsBukkit
            .isCoordsEquals(this, (Node) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }
}
