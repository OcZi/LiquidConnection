package me.oczi.api.iterator;

import me.oczi.api.TaskState;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.checkpoint.CheckpointANode;
import me.oczi.api.node.point.LiquidPointNode;
import me.oczi.util.Commons;
import me.oczi.util.CommonsNode;
import me.oczi.util.LiquidFace;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link LiquidIterator} in a 3D world.
 */
public class LiquidIterator3D extends AbstractLiquidIterator {
    private LiquidFace persistenceFace;

    public LiquidIterator3D(LiquidPointNode<LiquidNode> point,
                            CheckedSet<ALiquidNode> checkedSet,
                            @Nullable BlockFace ignoreFace) {
        super(point, checkedSet, ignoreFace);
        // If start node and goal node are on the same level (Y) of blocks
        // there is not need to check BlockFace up because
        // they cannot connect on a different level,
        // that violates the law of physics.
        // FIXME: Delete this after implement LiquidIterator2D
        if (CommonsNode.isSameYLevel(
            currentNode, point.getGoal())) {
            this.persistenceFace = LiquidFace.UP;
        }
    }

    @Override
    public boolean hasNext() {
        return this.state == TaskState.UNDEFINED;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public LiquidNode next() {
        if (this.state == TaskState.SUCCESSFULLY) {
            return this.currentNode;
        }

        if (this.tries == this.max ||
            this.currentNode == null) {
            this.state = TaskState.FAILED;
        } else {
            this.tries++;
        }

        if (this.state == TaskState.FAILED) {
            return null;
        }
        checkedSet.addToBlackSet(this.currentNode);
        Set<ALiquidNode> blocks = CommonsNode
            .getAdjacentLiquidNodes(
                this.currentNode,
                this.point,
                newIgnoreFaces());
        ALiquidNode nextNode = processNextNode(blocks);
        if (nextNode == null) {
            if (Commons.isNullOrEmpty(latestCheckpoint)) {
                if (Commons.isNullOrEmpty(checkpoints)) {
                    this.state = TaskState.FAILED;
                    return null;
                }
                this.latestCheckpoint = checkpoints.poll();
            }
            this.currentNode = this.latestCheckpoint.getNearestNode();
        } else {
            Block currentNodeBlock = this.currentNode.getBlock();
            Block nextNodeBlock = nextNode.getBlock();
            this.ignoreFace = LiquidFace.toLiquidFace(
                nextNodeBlock.getFace(currentNodeBlock));
            this.currentNode = nextNode;
        }
        return this.currentNode;
    }

    private ALiquidNode processNextNode(Set<ALiquidNode> nodes) {
        nodes.removeIf(checkedSet::isBlackSetted);
        if (Commons.isNullOrEmpty(nodes)) {
            return null;
        } else if (nodes.size() < 2) {
            ALiquidNode next = nodes.iterator().next();
            if (CommonsNode.isCoordsEquals(next, point.getGoal())) {
                this.state = TaskState.SUCCESSFULLY;
            }
            return next;
        } else {
            this.latestCheckpoint =
                CheckpointANode.newCheckpoint(currentNode, nodes);
            for (ALiquidNode node : latestCheckpoint.getNodes()) {
                if (CommonsNode.isCoordsEquals(node, point.getGoal())) {
                    this.state = TaskState.SUCCESSFULLY;
                    return node;
                }
            }
            checkpoints.offer(latestCheckpoint);
            return this.latestCheckpoint.getNearestNode();
        }
    }

    private Set<LiquidFace> newIgnoreFaces() {
        Set<LiquidFace> faces = new HashSet<>();
        if (ignoreFace != null) {
            faces.add(ignoreFace);
            ignoreFace = null;
        }
        if (persistenceFace != null) {
            faces.add(persistenceFace);
        }
        return faces;
    }
}
