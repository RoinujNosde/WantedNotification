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
package me.roinujnosde.wantednotification.managers;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import me.roinujnosde.wantednotification.Helper;
import me.roinujnosde.wantednotification.WantedNotification;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Edson Passos <edsonpassosjr@outlook.com>
 */
public class ConfigManager {

    private final WantedNotification plugin;

    private boolean emailEnabled;
    private boolean emailUseSsl;
    private boolean emailUseTls;
    private String emailHostname;
    private int emailPort;
    private String emailUsername;
    private String emailPassword;
    private String emailName;
    private String emailSubject;
    private String emailMessage;
    private List<String> emailReceivers;
    private boolean debug;
    private Map<UUID, String> wantedList;
    
    public ConfigManager(WantedNotification plugin) {
        this.plugin = Objects.requireNonNull(plugin);
        load();
    }

    public void load() {
        FileConfiguration config = plugin.getConfig();
        debug = config.getBoolean("debug");
        ConfigurationSection wlSection = config.getConfigurationSection("wanted-list");
        if (wlSection == null) {
            wlSection = config.createSection("wanted-list");
        }
        wantedList = Helper.stringMapToUUIDMap(wlSection.getValues(false));
        
        emailEnabled = config.getBoolean("email.enabled");
        emailUseSsl = config.getBoolean("email.use-ssl");
        emailUseTls = config.getBoolean("email.use-tls");
        emailHostname = config.getString("email.hostname");
        emailPort = config.getInt("email.port");
        emailUsername = config.getString("email.username");
        emailPassword = config.getString("email.password");
        emailName = config.getString("email.name");
        emailSubject = config.getString("email.subject");
        emailMessage = config.getString("email.message");
        emailReceivers = config.getStringList("email.receivers");
    }

    public void save() {
        plugin.getConfig().createSection("wanted-list", wantedList);
        plugin.saveConfig();
    }

    public boolean isDebug() {
        return debug;
    }

    public Map<UUID, String> getWantedMap() {
        return wantedList;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public boolean isEmailUseSSL() {
        return emailUseSsl;
    }

    public boolean isEmailUseTLS() {
        return emailUseTls;
    }

    public String getEmailHostname() {
        return emailHostname;
    }

    public int getEmailPort() {
        return emailPort;
    }

    public String getEmailUsername() {
        return emailUsername;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public String getEmailName() {
        return emailName;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public List<String> getEmailReceivers() {
        return emailReceivers;
    }
}
