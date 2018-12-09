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
package me.roinujnosde.wantednotification.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import me.roinujnosde.wantednotification.WantedNotification;
import me.roinujnosde.wantednotification.managers.WantedManager;

/**
 *
 * @author Edson Passos <edsonpassosjr@outlook.com>
 */
public class RemoveCommand extends WNCommand {

	private final WantedManager wantedManager;

	public RemoveCommand(WantedNotification plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args);
		this.wantedManager = new WantedManager(plugin);
	}

	@Override
	public boolean run() {
		if (args.length >= 2) {

			@SuppressWarnings("deprecation")
			OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
			if (player == null) {
				sender.sendMessage(plugin.getLang("invalid-player"));
				return true;
			}

			if (sender.hasPermission("wantednotification.remove")) {
				if (wantedManager.isWanted(player.getUniqueId())) {
					wantedManager.remove(player.getUniqueId());
					sender.sendMessage(plugin.getLang("removed"));
					return true;
				} else {
					sender.sendMessage(plugin.getLang("not-wanted"));
				}
			} else {
				sender.sendMessage(plugin.getLang("no-permission"));
			}
			return true;
		}
		return false;
	}

}
