/* $Id: IncrementQuestAction.java,v 1.9 2013/04/25 20:03:03 kiheru Exp $ */
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
package games.stendhal.server.entity.npc.action;

import games.stendhal.common.MathHelper;
import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.config.annotations.Dev;
import games.stendhal.server.core.config.annotations.Dev.Category;
import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.EventRaiser;
import games.stendhal.server.entity.player.Player;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Increments by some value the current state or substate of a quest.
 */
@Dev(category=Category.QUEST_SLOT, label="State")
public class IncrementQuestAction implements ChatAction {

	private final String questname;
	private final int index;
	private final int increment;

	/**
	 * Creates a new IncrementQuestAction.
	 *
	 * @param questname
	 *            name of quest-slot to change
	 * @param increment
	 *            the increment to the old value
	 */
	public IncrementQuestAction(final String questname, final int increment) {
		this.questname = questname;
		this.index = -1;
		this.increment = increment;
	}

	/**
	 * Creates a new IncrementQuestAction.
	 *
	 * @param questname
	 *            name of quest-slot to change
	 * @param index
	 *            index of sub state
	 * @param increment
	 *            increment to the old value
	 */
	@Dev
	public IncrementQuestAction(final String questname, @Dev(defaultValue="1") final int index, final int increment) {
		this.questname = questname;
		this.index = index;
		this.increment = increment;
	}

	@Override
	public void fire(final Player player, final Sentence sentence, final EventRaiser raiser) {
		String substate = player.getQuest(questname,index);
		int questcount;
		if (substate == null) {
			questcount = increment;
		} else {
			questcount = MathHelper.parseIntDefault(substate,0) + increment;
		}
		if (index > -1) {
			player.setQuest(questname, index, "" + questcount);
		} else {
			player.setQuest(questname, "" + questcount);
		}
	}

	@Override
	public String toString() {
		return "IncrementQuest<" + questname + "[" + index + "] + " + increment + ">";
	}


	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false,
				IncrementQuestAction.class);
	}
}
