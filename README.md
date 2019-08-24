What does this plugin do?
> It notifies via email and in game whenever a player on the wanted list joins the server.

Why?
> Maybe you want to question or investigate the player about something, but you don't know when he does log in.

Installation
1. Place the jar in the plugins folder
2. Start your server
3. Open the generated config.yml file
4. Configure the email section (if you have problems with it, there is a smtp-servers.txt file inside the jar of common configuration for Outlook, Gmail and Yahoo)
5. Restart your server



Configuration
```yaml
#Email settings
email:
    enabled: false
    use-ssl: true
    use-tls: true
    hostname: "smtp.googlemail.com"
    port: 465
    username: "johnnyjohnson@gmail.com"
    password: "password"
    name: "YourServer name"
    subject: "WantedNotification"
    message: "Hi,\nA player in the Wanted list just entered your server: @player\nRemember to remove him from the list: /wn remove @player\n \nYourServer name"
    receivers:
    - "bethpeterson@gmail.com"
    - "leopardowner@outlook.com"

#Do not edit manually, use the commands
wanted-list: []

#Send more output to the console?
debug: false

#Help command
help-command:
- "&a[WantedNotification] Help:"
- "&a/wn add <player> - adds a player to the wanted list"
- "&a/wn remove <player> - removes a player from the wanted list"
- "&a/wn list - lists the wanted players"
- "&a/wn help - shows this help"

#Messages used by the plugin
language:
    no-permission: "&a[WantedNotification] You don't have permission to do this."
    added: "&a[WantedNotification] Player added to the list!"
    removed: "&a[WantedNotification] Player removed from the list!"
    not-wanted: "&a[WantedNotification] This player is not Wanted!"
    already-wanted: "&a[WantedNotification] This player is already in the Wanted list."
    invalid-player: "&a[WantedNotification] This is not a valid player!"
    alert: "&a[WantedNotification] {0} has joined the server!"
    list-title: "&a[WantedNotification] Wanted List (total: {0})"
    list-line: "&a@player"
```

Commands
- /wn add <player>: Adds a player to the wanted list
- /wn list: Lists the wanted players
- /wn remove <player>: Removes a player from the list
- /wn help: Shows the commands

Permissions
- wantednotification.alert: Allows you to receive the notification.
- wantednotification.<command>: Allows you to use /wn <command>
