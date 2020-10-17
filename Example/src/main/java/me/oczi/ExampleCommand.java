package me.oczi;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.oczi.api.LiquidConnection;
import me.oczi.api.Pathfinding;
import me.oczi.api.TaskState;
import me.oczi.api.node.block.LiquidNode;
import me.oczi.api.region.Region;
import me.oczi.api.region.Regions;
import me.oczi.util.CommonsBukkit;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import net.kyori.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

@Command(
    names = "liquid-connect",
    desc = "Liquid connector example",
    permission = "liquid-connector.example")
public class ExampleCommand implements CommandClass {

  @Command(names = "connect")
  public boolean connect(@Sender CommandSender sender, Block start, Block goal) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players!");
      return false;
    }
    if (!start.isLiquid() || !goal.isLiquid()) {
      sender.sendMessage("Start or goal is not liquid!");
      return false;
    }

    runPathfinding(sender, null, start, goal);
    return true;
  }

  @Command(names = "see-path")
  public boolean seePath(@Sender CommandSender sender, Block start, Block goal) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players!");
      return false;
    }
    if (!start.isLiquid() || !goal.isLiquid()) {
      sender.sendMessage("Start or goal is not liquid!");
      return false;
    }

    runPathfinding(sender,
        n -> {
          Block b = n.getBlock();
          b.setType(Material.GRANITE);
          new BukkitRunnable() {

            @Override
            public void run() {
              b.setType(Material.WATER);
            }
          }.runTaskLater(ExampleMain.getPlugin(), 50L);
        },
        start,
        goal);
    return true;
  }

  public LiquidConnection runPathfinding(CommandSender sender,
                                        Consumer<LiquidNode> consumer,
                                        Block start,
                                        Block goal) {
    Region cuboid = Regions
        .newCuboidWithRadius(start,
            getDistanceBetween(start, goal));
    LiquidConnection connector = Pathfinding
        .liquidConnector(start,
            goal,
            cuboid.getBlocksBetweenLevel(
                start.getY(), goal.getY()),
            goal.getFace(start));
    long current = System.nanoTime();
    connector.run(
        consumer,
        node ->
            sender.sendMessage(serialize(
                TextComponent.of("Task result: ", TextColor.GRAY)
                    .append(
                        TextComponent.of(
                            connector.getState().toString(),
                            TextColor.AQUA))
                    .append(
                        TextComponent.of(", Nano elapsed:")
                            .append(
                                TextComponent.of(
                                    String.valueOf(
                                        (System.nanoTime() - current) / 1000000000.0),
                                    TextColor.AQUA))))),
        t -> {
          throw new CommandException(
              "Error while trying to pathfinding liquid block goal", t);
        });
    return connector;
  }

  @Command(names = "break")
  public boolean breakConnect(@Sender CommandSender sender, Block start, Block goal) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players!");
      return false;
    }
    if (!start.isLiquid() || !goal.isLiquid()) {
      sender.sendMessage("Start or goal is not liquid!");
      return false;
    }
    TaskState state = runPathfinding(
        sender,
        null,
        start,
        goal).getState();
    if (state != TaskState.SUCCESSFULLY) {
      return true;
    }
    if (goal.isLiquid()) {
      goal.setType(Material.AIR);
    } else if (CommonsBukkit.isActiveWaterLogged(goal)) {
      Waterlogged waterlogged = (Waterlogged) goal.getBlockData();
      waterlogged.setWaterlogged(false);
      goal.setBlockData(waterlogged);
    }
    return true;
  }

  public int getDistanceBetween(Block start, Block goal) {
    // Factor multiplier.
    // Apply to distance to make the region radius a bit more extended.
    double factor = 1.8;
    return (int) (start.getLocation()
        .distance(goal.getLocation()) * factor);
  }

  public String serialize(TextComponent component) {
    return LegacyComponentSerializer.INSTANCE.serialize(component);
  }
}
