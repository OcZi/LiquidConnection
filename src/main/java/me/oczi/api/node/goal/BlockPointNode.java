package me.oczi.api.node.goal;

import me.oczi.api.node.block.BlockNode;
import org.bukkit.Material;
import org.bukkit.block.Block;

public interface BlockPointNode<T extends BlockNode>
    extends PointNode<T> {

    Material getType();

    Block getBlock();
}
