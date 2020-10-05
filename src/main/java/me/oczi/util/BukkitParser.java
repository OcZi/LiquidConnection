package me.oczi.util;

import me.oczi.api.LiquidType;
import me.oczi.api.node.block.LiquidNode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import static com.google.common.base.Preconditions.checkArgument;
import static me.oczi.api.LiquidType.*;

public interface BukkitParser {

    static Location asLocation(World world, LiquidNode node) {
        return new Location(world,
            node.getX(),
            node.getY(),
            node.getZ());
    }

    static LiquidType asLiquid(Block block) {
        return asLiquid(block.getType());
    }

    static LiquidType asLiquid(Material material) {
        if (material == Material.WATER) {
            return WATER;
        } else if (material == Material.LAVA) {
            return LAVA;
        } else {
            return NONE;
        }
    }

    static LiquidType checkedAsLiquid(Block block, String err) {
        LiquidType liquidType = asLiquid(block);
        if (liquidType == LiquidType.NONE) {
            checkArgument(CommonsBukkit.isActiveWaterLogged(block), err);
            return LiquidType.WATER;
        }
        return liquidType;
    }

    static LiquidType uncheckedAsLiquid(Block block) {
        LiquidType liquidType = asLiquid(block);
        if (liquidType == NONE &&
            CommonsBukkit.isActiveWaterLogged(block)) {
            return WATER;
        }
        return liquidType;
    }
}
