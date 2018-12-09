package me.roinujnosde.wantednotification.commands;

import java.util.Objects;

import org.bukkit.command.CommandSender;

import me.roinujnosde.wantednotification.WantedNotification;

public abstract class WNCommand {

	protected WantedNotification plugin;
	protected CommandSender sender;
	protected String[] args;
	
	public WNCommand(WantedNotification plugin, CommandSender sender, String[] args) {
		this.plugin = Objects.requireNonNull(plugin);
		this.sender = Objects.requireNonNull(sender);
		this.args = args;
	}
	
	public abstract boolean run();
}
