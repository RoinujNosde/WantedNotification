package me.roinujnosde.wantednotification;

import java.text.MessageFormat;
import java.util.Objects;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.roinujnosde.wantednotification.managers.ConfigManager;

public class NotificationSender {

	private final WantedNotification plugin;
	private final ConfigManager cm;

	public NotificationSender(WantedNotification plugin) {
		this.plugin = Objects.requireNonNull(plugin);
		this.cm = new ConfigManager(plugin);
	}

	public void send(Player wanted) {
		notifyInGame(wanted);
		notifyViaEmail(wanted);
	}

	private void notifyViaEmail(Player wanted) {
		new BukkitRunnable() {
			public void run() {
				Email email = createEmail();
				addReceivers(email);

				send(email, wanted);
			}
		}.runTaskAsynchronously(plugin);
	}


	private String configureMessage(Player wanted) {
		String message = cm.getEmailMessage().replaceAll("@player", wanted.getName());
		return message;
	}
	
	private void send(Email email, Player wanted) {
		try {
			email.setFrom(cm.getEmailUsername(), cm.getEmailName());
			email.setSubject(cm.getEmailSubject());
			email.setMsg(configureMessage(wanted));
			email.send();
		} catch (EmailException ex) {
			plugin.debug("An error occurred while trying to send an email: ", false);
			ex.printStackTrace();
		}
	}

	private void notifyInGame(Player wanted) {
		Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission("wantednotification.alert"))
				.forEach(p -> p.sendMessage(MessageFormat.format(plugin.getLang("alert"), wanted.getName())));
	}

	private void addReceivers(Email email) {
		for (String receiver : cm.getEmailReceivers()) {
			plugin.debug("Receiver: " + receiver, true);

			try {
				email.addTo(receiver);
			} catch (EmailException ex) {
				plugin.debug(receiver + " is not a valid email!", false);
			}
		}
	}

	private Email createEmail() {
		Email email = new SimpleEmail();
		email.setHostName(cm.getEmailHostname());
		if (!cm.isEmailUseSSL()) {
			email.setSmtpPort(cm.getEmailPort());
		} else {
			email.setSslSmtpPort(Integer.toString(cm.getEmailPort()));
		}		
		
		email.setSSLOnConnect(cm.isEmailUseSSL());
		email.setStartTLSEnabled(cm.isEmailUseTLS());
		email.setAuthenticator(new DefaultAuthenticator(cm.getEmailUsername(), cm.getEmailPassword()));
		return email;
	}

}
