package me.oczi.api;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.oczi.api.region.Region;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface Pathfinding {

    static LiquidConnector liquidConnector(Block start,
                                           Block goal,
                                           Region region,
                                           @Nullable BlockFace face) {
        return new LiquidConnectorImpl(start, goal, region, face);
    }

    static LiquidConnector liquidConnector(Block start,
                                           Block goal,
                                           Region region,
                                           @Nullable BlockFace face,
                                           @Nullable ListeningExecutorService service) {
        return new LiquidConnectorImpl(start, goal, region, face, service);
    }

    static LiquidConnector liquidConnector(Block start,
                                           Block goal,
                                           Set<Block> blocks,
                                           @Nullable BlockFace face) {
        return new LiquidConnectorImpl(start, goal, blocks, face);
    }

    static LiquidConnector liquidConnector(Block start,
                                           Block goal,
                                           Set<Block> blocks,
                                           @Nullable BlockFace face,
                                           @Nullable ListeningExecutorService service) {
        return new LiquidConnectorImpl(start, goal, blocks, face, service);
    }
}
