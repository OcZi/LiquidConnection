package me.oczi.api;

import org.bukkit.Material;

/**
 * Liquid types.
 */
public enum LiquidType {
    WATER(Material.WATER),
    LAVA(Material.LAVA),
    NONE(Material.AIR);

    private final Material material;

    LiquidType(Material material) {
        this.material = material;
    }

    /**
     * Validates that all of types is water or lava.
     * @param types Types to check.
     * @return is valid.
     */
    public static boolean isValid(LiquidType... types) {
        for (LiquidType type : types) {
            if (type == NONE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get liquid material of LiquidType.
     * @return Material.
     */
    public Material getMaterial() {
        return material;
    }
}
