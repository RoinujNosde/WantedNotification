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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.roinujnosde.wantednotification.WantedNotification;

/**
 *
 * @author Edson Passos <edsonpassosjr@outlook.com>
 */
public class WNCommandExecutor implements CommandExecutor, TabCompleter {

	private final WantedNotification plugin;

	public WNCommandExecutor(WantedNotification plugin) {
		this.plugin = Objects.requireNonNull(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length >= 1) {
			try {
				String commandName = new Character(args[0].charAt(0)).toString().toUpperCase() + args[0].substring(1)
						+ "Command";
				@SuppressWarnings("unchecked")
				Class<WNCommand> clazz = (Class<WNCommand>) Class
						.forName("me.roinujnosde.wantednotification.commands." + commandName);
				WNCommand cmd = clazz.getConstructor(WantedNotification.class, CommandSender.class, String[].class)
						.newInstance(plugin, sender, args);
				return cmd.run();
			} catch (Exception ignored) {
			}
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias,
			String[] args) {
		if (args.length == 1) {
			//TODO: Testar
			return Arrays.asList("add", "help", "remove", "list");
		}
		
		return null;
	}
}
