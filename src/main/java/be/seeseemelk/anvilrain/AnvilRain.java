package be.seeseemelk.anvilrain;

import org.bukkit.plugin.java.JavaPlugin;

public class AnvilRain extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		AnvilRainer.setPLugin(this);
		new AnvilRainer().runTaskTimer(this, 10, 10);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
