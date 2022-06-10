package me.cubecrafter.woolwars.menu.menus;

import me.cubecrafter.woolwars.menu.Menu;
import me.cubecrafter.woolwars.menu.MenuItem;
import me.cubecrafter.woolwars.utils.ArenaUtil;
import me.cubecrafter.woolwars.utils.ItemBuilder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {

    public MainMenu(Player player) {
        super(player);
    }

    @Override
    public String getTitle() {
        return "● Play Wool Wars";
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public List<MenuItem> getItems() {
        List<MenuItem> items = new ArrayList<>();
        addFiller(new ItemBuilder("GRAY_STAINED_GLASS_PANE").setDisplayName("&f").build(), Arrays.asList(0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,42,43,44));

        items.add(new MenuItem(22, new ItemBuilder("WOODEN_SWORD").setDisplayName("&dWool Wars").setLore(Arrays.asList("&eClick to play!", "&7{count_total} currently playing!")).build()).addAction(e -> {
            closeMenu();
            ArenaUtil.joinRandom(player);
        }).setClickSound("UI_BUTTON_CLICK"));

        items.add(new MenuItem(39, new ItemBuilder("CLOCK").setDisplayName("&aAvailable Arenas").setLore(Arrays.asList("&eClick to browse!")).build()).addAction(e -> {
            new ArenaListMenu(player).openMenu();
        }).setClickSound("UI_BUTTON_CLICK"));

        items.add(new MenuItem(40, new ItemBuilder("BARRIER").setDisplayName("&cClose").build()).addAction(e -> {
            closeMenu();
        }).setClickSound("UI_BUTTON_CLICK"));

        items.add(new MenuItem(41, new ItemBuilder("PAPER").setDisplayName("&aStatistics").setLore(Arrays.asList("&eClick to view your stats!")).build()).addAction(e -> {
            new StatsMenu(player).openMenu();
        }).setClickSound("UI_BUTTON_CLICK"));

        return items;
    }

    @Override
    public boolean update() {
        return true;
    }

}
