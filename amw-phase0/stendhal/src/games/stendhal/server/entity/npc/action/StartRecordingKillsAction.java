/* $Id: StartRecordingKillsAction.java,v 1.19 2013/04/25 20:03:03 kiheru Exp $ */
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

import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.config.annotations.Dev;
import games.stendhal.server.core.config.annotations.Dev.Category;
import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.EventRaiser;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.util.RequiredKillsInfo;

import java.util.HashMap;
import java.util.Map;

import marauroa.common.Pair;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Starts the recording of kills.
 *
 * @see games.stendhal.server.entity.npc.condition.KilledForQuestCondition
 * @see games.stendhal.server.entity.npc.condition.KilledInSumForQuestCondition
 *
 * @author hendrik
 */
@Dev(category=Category.KILLS, label="State")
public class StartRecordingKillsAction implements ChatAction {
	// first number in pair is required solo kills, second is required shared kills
	private final Map<String, Pair<Integer, Integer>> toKill;
	private final String QUEST_SLOT;
	private final int KILLS_INDEX;

	/**
	 * Creates a new StartRecordingKillsAction.
	 *
	 * @param questSlot name of quest slot
	 * @param index index within quest slot
	 * @param toKill creatures which should be killed by the player (name, required solo kills, required solo/shared kills)
	 */
	public StartRecordingKillsAction(final String questSlot, @Dev(defaultValue="1") final int index,
			final Map<String, Pair<Integer, Integer>> toKill) {
		this.toKill = toKill;
		this.QUEST_SLOT = questSlot;
		this.KILLS_INDEX = index;
	}

	/**
	 * Creates a new StartRecordingKillsAction.
	 *
	 * @param questSlot name of quest slot
	 * @param index index within quest slot
	 * @param requiredKills creatures which should be killed by the player (name, required solo kills, required solo/shared kills)
	 */
	@Dev
	public StartRecordingKillsAction(final String questSlot, @Dev(defaultValue="1") final int index,
			final RequiredKillsInfo... requiredKills) {
		this.toKill = new HashMap<String, Pair<Integer, Integer>>();
		for (RequiredKillsInfo info : requiredKills) {
			toKill.put(info.getName(), new Pair<Integer, Integer>(info.getRequiredSolo(), info.getRequiredMaybeShared()));
		}
		this.QUEST_SLOT = questSlot;
		this.KILLS_INDEX = index;
	}

	/**
	 * Creates a new StartRecordingKillsAction.
	 *
	 * @param questSlot name of quest slot
	 * @param index index within questslot
	 * @param creature Creature
	 * @param requiredSolo number of creatures that have to be killed solo
	 * @param requiredShared number of creatures that may be killed with help by other players
	 */
	public StartRecordingKillsAction(final String questSlot, final int index, String creature,
			int requiredSolo, int requiredShared) {
		this.toKill = new HashMap<String, Pair<Integer, Integer>>();
		toKill.put(creature, new Pair<Integer, Integer>(requiredSolo, requiredShared));
		this.QUEST_SLOT = questSlot;
		this.KILLS_INDEX = index;
	}

	@Override
	public void fire(final Player player, final Sentence sentence, final EventRaiser npc) {
		final StringBuilder sb = new StringBuilder("");
		for (final String creature : toKill.keySet()) {
			final int requiredSolo = toKill.get(creature).first();
			final int requiredShared = toKill.get(creature).second();
			final int soloKills = player.getSoloKill(creature);
			final int sharedKills = player.getSharedKill(creature);
			sb.append(creature + "," + requiredSolo + "," + requiredShared + "," + soloKills + ","
					+ sharedKills + ",");
		}
		final String result = sb.toString().substring(0, sb.toString().length() - 1);
		player.setQuest(QUEST_SLOT, KILLS_INDEX, result);
	}

	@Override
	public String toString() {
		return "StartRecordingKillsActions <" + toKill.toString() + ">";
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false, StartRecordingKillsAction.class);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(toKill).toHashCode();
	}

}
