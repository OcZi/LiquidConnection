package me.oczi.util;

import me.oczi.api.node.checkpoint.CheckpointANode;

import java.util.Collection;

public interface Commons {

    static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean isNullOrEmpty(CheckpointANode<?> node) {
        return node == null || node.isEmpty();
    }
}
