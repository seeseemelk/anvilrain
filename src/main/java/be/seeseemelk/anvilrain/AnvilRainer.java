package be.seeseemelk.anvilrain;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AnvilRainer extends BukkitRunnable
{
	private static AnvilRain plugin;
	private static int size = 20;
	
	public static void setPLugin(AnvilRain plugin)
	{
		AnvilRainer.plugin = plugin;
	}
	
	public double random(double min, double max)
	{
		return Math.random() * (max - min) + min;
	}
	
	public void removeMark(Block block)
	{
		block.removeMetadata("anvilrain_mark", plugin);
	}
	
	@Override
	public void run()
	{
		for (Player player : Bukkit.getOnlinePlayers())
		{
			if (player.getWorld().hasStorm())
			{
				makeItRain(player);
			}
		}
	}
	
	public void makeItRain(Player player)
	{
		for (int i = 0; i < 5; i++)
		{
			Location location = player.getLocation().add(random(-size, size), size, random(-size, size));
			Block block = location.getBlock();
			if (block.getType() == Material.AIR)
			{
				block.setType(Material.ANVIL);
				new BukkitRunnable()
				{
					@Override
					public void run()
					{
						Location loc = location;
						for (int limit = 0; limit < 50; limit++)
						{
							if (loc.getBlock().getType() == Material.ANVIL)
							{
								loc.getBlock().setType(Material.AIR);
								break;
							}
							loc = loc.add(0, -1, 0);
						}
					}
				}.runTaskLater(plugin, 60);
			}
		}
	}
}
















