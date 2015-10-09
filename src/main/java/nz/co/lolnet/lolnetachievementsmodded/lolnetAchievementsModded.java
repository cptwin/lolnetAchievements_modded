package nz.co.lolnet.lolnetachievementsmodded;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import java.util.Map;

@Mod(modid = lolnetAchievementsModded.MODID, version = lolnetAchievementsModded.VERSION, name = lolnetAchievementsModded.MODNAME)
public class lolnetAchievementsModded
{
	public static final String MODNAME = "lolnet Achievements Modded";
    public static final String MODID = "lolnetAchievementsModded";
    public static final String VERSION = "1.0";//You also need to update the version in build.gradle to match this

	//This allows the mod to be server side only
	@NetworkCheckHandler
	public boolean networkCheck(Map<String, String> map, Side side) { return true; }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event);
		FMLCommonHandler.instance().bus().register(new EventHooks());
		MinecraftForge.EVENT_BUS.register(new EventHooks());
	}

	//An example showing how to get all players currently in game and check what achievements they have
	public static void printPlayerAchievements(){
		if (FMLCommonHandler.instance().getMinecraftServerInstance() == null) return; //Return in the server is not running. Probably not necessary

		List allPlayers = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList;
		List allAchievements = AchievementList.achievementList;

		for (Object playerObject : allPlayers){
			if (!(playerObject instanceof EntityPlayerMP)) continue;

			EntityPlayerMP player = (EntityPlayerMP) playerObject;
			LogHelper.info(player.getCommandSenderName());	//Prints the in game name of the player

			for (Object achievementObject : allAchievements){
				Achievement achievement = (Achievement) achievementObject;

				LogHelper.info(achievement.statId);											//Prints the achievement id e.g. "achievement.ae2.Facade"
				LogHelper.info(StatCollector.translateToLocal(achievement.statId)); 		//This will get the localized name of the achievement
				LogHelper.info(player.func_147099_x().hasAchievementUnlocked(achievement)); //Prints true or false depending on weather or not the player has this achievement unlocked
			}
		}
	}
}
