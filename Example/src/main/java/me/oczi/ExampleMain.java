package me.oczi;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.translator.DefaultMapTranslationProvider;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import me.oczi.commandflow.BlockFactory;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMain extends JavaPlugin {

  @Override
  public void onEnable() {
    CommandManager commandManager = new BukkitCommandManager(getName());
    AnnotatedCommandTreeBuilder builder =
        AnnotatedCommandTreeBuilder.create(createPartInjector());
    commandManager.setTranslator(
        new DefaultTranslator(
            new DefaultMapTranslationProvider()));
    commandManager.registerCommands(
        builder.fromClass(new ExampleCommand()));
  }

  private PartInjector createPartInjector() {
    SimplePartInjector injector = new SimplePartInjector();
    injector.bindFactory(Block.class, new BlockFactory());
    injector.install(new BukkitModule());
    return injector;
  }

  public static ExampleMain getPlugin() {
    return getPlugin(ExampleMain.class);
  }
}
