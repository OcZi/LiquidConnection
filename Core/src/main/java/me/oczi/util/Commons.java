package me.oczi.util;

import me.oczi.api.node.checkpoint.CheckpointANode;

import java.util.Collection;

/**
 * Commons utils.
 */
public interface Commons {

    /**
     * Check is null or empty.
     * @param collection Collection to check.
     * @return is null or empty.
     */
    static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Check is null or empty.
     * @param node Checkpoint to check.
     * @return is null or empty.
     */
    static boolean isNullOrEmpty(CheckpointANode<?> node) {
        return node == null || node.isEmpty();
    }
}
