package me.oczi;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import me.oczi.api.LiquidType;
import me.oczi.api.TaskState;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.iterator.LiquidIterator;
import me.oczi.api.iterator.LiquidIterator3D;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.goal.LiquidPointNode;
import me.oczi.api.region.Region;
import me.oczi.util.BukkitParser;
import me.oczi.util.guava.GFutures;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class LiquidConnector implements Iterable<LiquidNode> {
    private final LiquidPointNode<LiquidNode> liquidPointNode;

    private final LiquidIterator iterator;
    private final Set<Block> blocks;

    private LiquidNode result;

    public LiquidConnector(Block start,
                           Block goal,
                           Region region,
                           @Nullable BlockFace face) {
        this(start, goal, region.getBlocks(), face);
    }

    public LiquidConnector(Block start,
                           Block goal,
                           Set<Block> blocks,
                           @Nullable BlockFace face) {
        LiquidType startType = BukkitParser
            .checkedAsLiquid(start,
                "The start block is not a liquid.");
        LiquidType goalType = BukkitParser
            .checkedAsLiquid(goal,
                "The goal block is not a liquid.");
        this.liquidPointNode = LiquidPointNode.pointOf(
            LiquidNode.newNode(startType, start),
            LiquidNode.newNode(goalType, goal));
        this.blocks = blocks;
        this.iterator = createIterator(face);
    }

    public LiquidNode run() {
        return run(null);
    }

    public LiquidNode run(@Nullable Consumer<LiquidNode> consumer) {
        while (iterator.hasNext()) {
            this.result = iterator.next();
            if (consumer != null) {
                consumer.accept(this.result);
            }
        }
        return this.result;
    }

    public void run(@Nullable Consumer<LiquidNode> success,
                    @Nullable Consumer<Throwable> failure) {
        run(null, success, failure);
    }

    public void run(@Nullable Consumer<LiquidNode> consumer,
                    @Nullable Consumer<LiquidNode> success,
                    @Nullable Consumer<Throwable> failure) {
        try {
            run(consumer);
            if (getState() == TaskState.SUCCESSFULLY) {
                if (success == null) return;
                success.accept(getResult());
            }
        } catch (Throwable t) {
            if (failure == null) return;
            failure.accept(t);
        }
    }

    public void runAsync(Consumer<LiquidNode> success,
                         Consumer<Throwable> failure) {
        runAsync(null, success, failure);
    }

    public void runAsync(@Nullable Consumer<LiquidNode> runnable,
                         Consumer<LiquidNode> success,
                         Consumer<Throwable> failure) {
        ListenableFuture<LiquidNode> submit =
            AsyncThread.submit(() -> run(runnable));
        Futures.addCallback(submit,
            GFutures.newFutureCallback(
                success, failure));
    }

    private LiquidIterator createIterator(BlockFace face) {
        CheckedSet<ALiquidNode> checkedSet =
            CheckedSet.newCheckedSet(
                ALiquidNode
                    .toSetNodes(
                        blocks, liquidPointNode),
                new HashSet<>());
        return new LiquidIterator3D(
            liquidPointNode,
            checkedSet,
            face);
    }

    public LiquidNode getResult() {
        return result;
    }

    @NotNull
    @Override
    public LiquidIterator iterator() {
        return iterator;
    }

    public TaskState getState() {
        return iterator.getState();
    }
}
