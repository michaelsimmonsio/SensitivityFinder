# SensitivityFinder
A simple plugin that can find any player's Minecraft sensitivity by their mouse movements.

### Accuracy

After testing, the plugin can guess a user's sensitivity to the closest 0.5. For simplicity, the plugin will show both the raw value detected (rounded to the nearest 0.00001) and a rounded value (rounded to the nearest 1).
The plugin works for all valid Minecraft sensitivity values.

### Usage

Once the plugin is installed, run the command `/sensitivity <player>` - this will start analyzing the player's movements.
After 100 ticks of movement have been stored, the plugin will find their sensitivity. This will repeat infinitely until you stop analyzing the player.
To stop analyzing the player, re-run the `/sensitivity <player>` command.

### Permissions
`sensitivity.admin` is required to run commands
`sensitivity.notify` is required to receive
