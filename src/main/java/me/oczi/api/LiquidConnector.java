package me.oczi.api;

import me.oczi.api.node.block.LiquidNode;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface LiquidConnector extends Iterable<LiquidNode> {

    /**
     * Run Pathfinding.
     * @return LiquidNode of result, or null.
     */
    LiquidNode run();

    /**
     * Run Pathfinding.
     * @param consumer Consumer executed every iteration of pathfinding.
     * @return LiquidNode of result, or null.
     */
    LiquidNode run(@Nullable Consumer<LiquidNode> consumer);

    /**
     * Run Pathfinding.
     * @param after Consumer executed after pathfinding.
     * @param failure Consumer executed if pathfinding throws a exception.
     */
    void run(@Nullable Consumer<LiquidNode> after,
             @Nullable Consumer<Throwable> failure);

    /**
     * Run Pathfinding.
     * @param consumer Consumer executed every iteration of pathfinding.
     * @param after Consumer executed after pathfinding.
     * @param failure Consumer executed if pathfinding throws a exception.
     */
    void run(@Nullable Consumer<LiquidNode> consumer,
             @Nullable Consumer<LiquidNode> after,
             @Nullable Consumer<Throwable> failure);

    /**
     * Run Pathfinding asynchronous.
     * @param after Consumer executed after pathfinding.
     * @param failure Consumer executed if pathfinding throws a exception.
     */
    void runAsync(Consumer<LiquidNode> after,
                  Consumer<Throwable> failure);

    /**
     * Run Pathfinding asynchronous.
     * @param after Consumer executed after pathfinding.
     * @param failure Consumer executed if pathfinding throws a exception.
     */
    void runAsync(@Nullable Consumer<LiquidNode> runnable,
                  Consumer<LiquidNode> after,
                  Consumer<Throwable> failure);

    /**
     * Get result of pathfinding.
     * @return Result Liquid node, or null if pathfinding has not started or has failed.
     */
    LiquidNode getResult();

    /**
     * Get actual state of pathfinding.
     * @return State of pathfinding.
     */
    TaskState getState();
}
