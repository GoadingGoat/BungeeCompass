/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.io.ByteArrayDataOutput
 *  com.google.common.io.ByteStreams
 *  org.bukkit.Server
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.plugin.messaging.Messenger
 */
package me.netindev;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.logging.Logger;
import me.netindev.comandos.Comando;
import me.netindev.listener.Eventos;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class Main
extends JavaPlugin {
    public static Plugin plugin;

    public void onEnable() {
        plugin = this;
        this.getCommand("bungeecompass").setExecutor((CommandExecutor)new Comando());
        this.getServer().getPluginManager().registerEvents((Listener)new Eventos(this), (Plugin)this);
        this.saveDefaultConfig();
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        this.getLogger().info("/ Plugin enabled, V: " + this.getDescription().getVersion() + ", created by: netindev.");
    }

    public static void enviarPlayer(Player jogador, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        jogador.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}

