package me.oczi.api.node.block;

import me.oczi.api.node.Node;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public interface BlockNode extends Node {

    Material getType();

    Block getBlock();

    Location getBlockLocation();
}
