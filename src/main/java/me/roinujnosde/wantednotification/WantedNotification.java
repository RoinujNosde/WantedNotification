/*
 * The MIT License
 *
 * Copyright 2018 Edson Passos <edsonpassosjr@outlook.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package me.roinujnosde.wantednotification;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.roinujnosde.wantednotification.commands.WNCommandExecutor;
import me.roinujnosde.wantednotification.listeners.PlayerJoinListener;
import me.roinujnosde.wantednotification.managers.ConfigManager;

/**
 *
 * @author Edson Passos <edsonpassosjr@outlook.com>
 */
public final class WantedNotification extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        configManager = new ConfigManager(this);

        getCommand("wantednotification").setExecutor(new WNCommandExecutor(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    /**
     * Sends a message to the console
     *
     * @param message message to send
     * @param respectUserDecision should the message be sent if debug is false?
     */
    public void debug(String message, boolean respectUserDecision) {
        if (respectUserDecision) {
            if (!configManager.isDebug()) {
                return;
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[WantedNotification] " + message);
    }

    public String getLang(String path) {
        if (path == null) {
            return null;
        }
        String lang = getConfig().getString("language."+path);
        if (lang == null) {
            lang = "";
        }
        lang = ChatColor.translateAlternateColorCodes('&', lang);

        return lang;
    }
}
