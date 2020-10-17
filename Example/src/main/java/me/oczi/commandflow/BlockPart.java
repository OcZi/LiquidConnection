package me.oczi.commandflow;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BlockPart implements ArgumentPart {
  private final String name;
  private final boolean consumeAll;
  private final Pattern blockPattern = Pattern.compile("^-?\\d+\\,-?\\d+\\,-?\\d+$");

  public BlockPart(String name) {
    this(name, false);
  }

  public BlockPart(String name, boolean consumeAll) {
    this.name = name;
    this.consumeAll = consumeAll;
  }

  @Override
  public List<Block> parseValue(CommandContext context,
                                ArgumentStack stack)
      throws ArgumentParseException {
    CommandSender sender = context.getObject(
        CommandSender.class,
        BukkitCommandManager.SENDER_NAMESPACE);
    if (!(sender instanceof Player)) {
      throw new ArgumentParseException(
          "Needs to be a player to get the world of block!");
    }
    World world = ((Player) sender).getWorld();
    List<Block> blocks = new ArrayList<>();

    try {
      if (consumeAll) {
        while (stack.hasNext()) {
          String next = stack.next();
          addCoordinates(next, world, blocks);
        }
      } else {
        addCoordinates(stack.next(), world, blocks);
      }
    } catch (NumberFormatException e) {
      throw new ArgumentParseException(
          "Bad format of coordinates!", e);
    }
    return blocks;
  }

  private void addCoordinates(String next, World world, List<Block> blocks) {
    if (blockPattern.matcher(next).matches()) {
      List<Integer> axis = toCoordinates(next.split(","));
      blocks.add(world.getBlockAt(
          axis.get(0), axis.get(1), axis.get(2)));
    }
  }

  private List<Integer> toCoordinates(String[] coordinates) {
    List<Integer> ints = new ArrayList<>();
    for (String coordinate : coordinates) {
      ints.add(Integer.parseInt(coordinate));
    }
    return ints;
  }

  @Override
  public Type getType() {
    return Block.class;
  }

  @Override
  public String getName() {
    return name;
  }
}
