package nz.co.lolnet.lolnetachievementsmodded;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by Brandon on 7/04/2015.
 */
public class ConfigHandler
{
	public static Configuration config;

	public static int anInt;
	public static String aString;

	public static void init(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());

		try
		{
			anInt = config.get(Configuration.CATEGORY_GENERAL, "anInt", 500, "an integer value", 1, Integer.MAX_VALUE).getInt(500);
			aString = config.get(Configuration.CATEGORY_GENERAL, "aString", "default String", "This is a string").getString();
		}
		catch (Exception e)
		{
			LogHelper.error("Error loading config file");
			e.printStackTrace();
		}
		finally
		{
			if (config.hasChanged()) config.save();
		}
	}
}
