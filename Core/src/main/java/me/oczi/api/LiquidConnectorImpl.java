package me.oczi.api;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import me.oczi.api.collections.CheckedSet;
import me.oczi.api.iterator.LiquidIterator;
import me.oczi.api.iterator.LiquidIterator3D;
import me.oczi.api.node.block.ALiquidNode;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.node.point.LiquidPointNode;
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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Class that's apply pathfinding to found
 * if two blocks of the same liquid is connected.
 */
public class LiquidConnectorImpl implements LiquidConnector {
    private final ListeningExecutorService service;
    private final LiquidPointNode<LiquidNode> liquidPointNode;

    private final LiquidIterator iterator;
    private final Set<Block> blocks;

    private LiquidNode result;

    LiquidConnectorImpl(Block start,
                               Block goal,
                               Region region,
                               @Nullable BlockFace face) {
        this(start, goal, region.getBlocks(), face, null);
    }

    LiquidConnectorImpl(Block start,
                               Block goal,
                               Region region,
                               @Nullable BlockFace face,
                               @Nullable ListeningExecutorService service) {
        this(start, goal, region.getBlocks(), face, service);
    }

    LiquidConnectorImpl(Block start,
                               Block goal,
                               Set<Block> blocks,
                               @Nullable BlockFace face) {
        this(start, goal, blocks, face, null);
    }

    LiquidConnectorImpl(Block start,
                               Block goal,
                               Set<Block> blocks,
                               @Nullable BlockFace face,
                               @Nullable ListeningExecutorService service) {
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
        this.service = service;
    }

    @Override
    public LiquidNode run() {
        return run(null);
    }

    @Override
    public LiquidNode run(@Nullable Consumer<LiquidNode> consumer) {
        while (iterator.hasNext()) {
            this.result = iterator.next();
            if (consumer != null) {
                consumer.accept(this.result);
            }
        }
        return this.result;
    }

    @Override
    public void run(@Nullable Consumer<LiquidNode> after,
                    @Nullable Consumer<Throwable> failure) {
        run(null, after, failure);
    }

    @Override
    public void run(@Nullable Consumer<LiquidNode> consumer,
                    @Nullable Consumer<LiquidNode> after,
                    @Nullable Consumer<Throwable> failure) {
        try {
            run(consumer);
            if (after == null) return;
            after.accept(getResult());
        } catch (Throwable t) {
            if (failure == null) return;
            failure.accept(t);
        }
    }

    @Override
    public void runAsync(Consumer<LiquidNode> after,
                         Consumer<Throwable> failure) {
        runAsync(null, after, failure);
    }

    @Override
    public void runAsync(@Nullable Consumer<LiquidNode> runnable,
                         Consumer<LiquidNode> after,
                         Consumer<Throwable> failure) {
        checkNotNull(service,
            "ExecutorService of LiquidConnector is null.");
        ListenableFuture<LiquidNode> submit =
            service.submit(() -> run(runnable));
        Futures.addCallback(submit,
            GFutures.newFutureCallback(
                after, failure));
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

    @Override
    public LiquidNode getResult() {
        return result;
    }

    @NotNull
    @Override
    public LiquidIterator iterator() {
        return iterator;
    }

    @Override
    public TaskState getState() {
        return iterator.getState();
    }
}
