package me.cubecrafter.woolwars.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.cubecrafter.woolwars.WoolWars;
import me.cubecrafter.woolwars.api.database.PlayerData;
import me.cubecrafter.woolwars.utils.ArenaUtil;
import org.bukkit.entity.Player;

public class PlaceholderHook extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "woolwars";
    }

    @Override
    public String getAuthor() {
        return "CubeCrafter";
    }

    @Override
    public String getVersion() {
        return WoolWars.getInstance().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null) return null;
        PlayerData data = WoolWars.getInstance().getPlayerDataManager().getPlayerData(player);
        switch (params) {
            case "wins":
                return String.valueOf(data.getWins());
            case "losses":
                return String.valueOf(data.getLosses());
            case "games_played":
                return String.valueOf(data.getGamesPlayed());
            case "kills":
                return String.valueOf(data.getKills());
            case "deaths":
                return String.valueOf(data.getDeaths());
            case "wool_placed":
                return String.valueOf(data.getWoolPlaced());
            case "blocks_broken":
                return String.valueOf(data.getBlocksBroken());
            case "powerups_collected":
                return String.valueOf(data.getPowerUpsCollected());
            case "selected_kit":
                return data.getSelectedKit();
        }
        String[] args = params.split("_");
        if (args[0].equals("count")) {
            if (args[1].equals("total")) {
                return String.valueOf(ArenaUtil.getArenas().stream().mapToInt(arena -> arena.getPlayers().size()).sum());
            }
            return String.valueOf(ArenaUtil.getArenasByGroup(args[1]).stream().mapToInt(arena -> arena.getPlayers().size()).sum());
        }
        return null;
    }

}
