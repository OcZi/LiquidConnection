package me.oczi.api.node.goal;

import me.oczi.api.LiquidType;
import me.oczi.api.node.block.LiquidNode;

public interface LiquidPointNode<T extends LiquidNode>
    extends BlockPointNode<T> {

    static <T extends LiquidNode> LiquidPointNode<T> pointOf(T start, T goal) {
        return new LiquidPointNodeImpl<>(start, goal);
    }

    LiquidType getLiquidType();
}
