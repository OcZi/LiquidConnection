package me.oczi.api.iterator;

import me.oczi.api.collections.CheckedSet;
import me.oczi.api.TaskState;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.LiquidNode;

import java.util.Iterator;

/**
 * Liquid iterator to search
 * if Start node and Goal node is connected.
 */
public interface LiquidIterator extends Iterator<LiquidNode> {

    TaskState getState();

    CheckedSet<ALiquidNode> getCheckedSet();
}