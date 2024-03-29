/* $Id: PlayerHasCompletedAchievementsCondition.java,v 1.6 2013/04/25 20:47:09 kiheru Exp $ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.npc.condition;

import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.config.annotations.Dev;
import games.stendhal.server.core.config.annotations.Dev.Category;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.npc.ChatCondition;
import games.stendhal.server.entity.player.Player;

import java.util.Arrays;
import java.util.List;

/**
 * has the player completed the specified number of the specified achievements?
 *
 * @author madmetzger
 */
@Dev(category=Category.STATS, label="Achievement?")
public class PlayerHasCompletedAchievementsCondition implements ChatCondition {

	private final List<String> achievements;

	private final int minimumToComplete;

	/**
	 * PlayerHasCompletedAchievementsCondition
	 *
	 * @param minimum required number of achievements, -1 means all
	 * @param achievementIdentifiers list of achievements to check
	 */
	@Dev
	public PlayerHasCompletedAchievementsCondition(@Dev(defaultValue="-1") int minimum, String... achievementIdentifiers) {
		achievements = Arrays.asList(achievementIdentifiers);
		if (minimum > -1) {
			minimumToComplete = minimum;
		} else {
			minimumToComplete = achievementIdentifiers.length;
		}
	}

	/**
	 * PlayerHasCompletedAchievementsCondition
	 *
	 * @param achievementIdentifiers list of achievements to check
	 */
	public PlayerHasCompletedAchievementsCondition(String... achievementIdentifiers) {
		this(-1, achievementIdentifiers);
	}

	@Override
	public boolean fire(Player player, Sentence sentence, Entity npc) {
		int reached = 0;
		for (String achievementIdentifier : achievements) {
			if(player.hasReachedAchievement(achievementIdentifier)) {
				reached = reached + 1;
			}
			if(reached >= minimumToComplete) {
				return true;
			}
		}
		return false;
	}

}
