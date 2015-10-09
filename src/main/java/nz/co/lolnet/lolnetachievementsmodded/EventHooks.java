package nz.co.lolnet.lolnetachievementsmodded;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.AchievementEvent;

/**
 * Created by brandon3055 on 9/10/2015.
 */
public class EventHooks {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onAchievementGetEvent(AchievementEvent event) {
		if (event.isCanceled()) return; //If this achievement has been canceled by another mod this code should not run. This is wy priority is set to lowest

		LogHelper.info(event.entityPlayer.getCommandSenderName());					//Prints the in game name of the player
		LogHelper.info(event.achievement.statId);									//Prints the achievement id e.g. "achievement.ae2.Facade"
		LogHelper.info(StatCollector.translateToLocal(event.achievement.statId)); 	//This will print the localized name of the achievement
		LogHelper.info(event.achievement.getDescription());							//Prints the achievement description
	}

	@SubscribeEvent
	public void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
		/*Ensures this code is only run server side because to do this client side requires different code.
		This should not be required with a server side mod its just here for single player testing*/
		if (!(event.player instanceof EntityPlayerMP)) return;

		EntityPlayerMP player = (EntityPlayerMP) event.player;

		//Prints the players username
		LogHelper.info(player.getCommandSenderName());

		//Prints all achievements including mod achievements and checks if the player has said achievement unlocked
		for (Object object : AchievementList.achievementList){
			if (!(object instanceof Achievement)) continue; //This probably isn't required

			Achievement achievement = (Achievement)object;

			LogHelper.info(achievement);
			LogHelper.info(player.func_147099_x().hasAchievementUnlocked(achievement));
		}

	}
}
