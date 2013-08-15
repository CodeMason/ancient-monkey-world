/* $Id: MakeupArtistNPC.java,v 1.26 2013/04/25 21:30:20 kiheru Exp $ */
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
package games.stendhal.server.maps.ados.city;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.OutfitChangerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.OutfitChangerBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates the NPCs and portals in Ados City.
 *
 * @author hendrik
 */
public class MakeupArtistNPC implements ZoneConfigurator {

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildFidorea(zone, 20, 13);
	}

	/**
	 * creates Fidorea in the specified zone
	 *
	 * @param zone StendhalRPZone
	 * @param x x
	 * @param y y
	 */
	public void buildFidorea(final StendhalRPZone zone, int x, int y) {
		final SpeakerNPC npc = new SpeakerNPC("Fidorea") {

			@Override
			protected void createPath() {
				// npc does not move
				setPath(null);
			}

			@Override
			protected void createDialog() {
				addGreeting("Hi, there. Do you need #help with anything?");
				addHelp("I sell masks. If you don't like your mask, you can #return and I will remove it, or you can just wait two hours, until it wears off.");
				
				// this is a hint that one of the items Anna wants is a dress (goblin dress)
				addQuest("Are you looking for toys for Anna? She loves my costumes, perhaps she'd like a #dress to try on. If you already got her one, I guess she'll have to wait till I make more costumes!"); 
				addJob("I am a makeup artist.");
				addReply(
				        "dress",
				        "I read stories that goblins wear a dress as a kind of armor! If you're scared of goblins, like me, maybe you can buy a dress somewhere. ");
				//addReply("offer", "Normally I sell masks. But I ran out of clothes and cannot by new ones until the cloth seller gets back from his search.");
				addGoodbye("Bye, come back soon.");

				final Map<String, Integer> priceList = new HashMap<String, Integer>();
				priceList.put("mask", 20);
				// if you change the wear off time, change her Help message too please
				final OutfitChangerBehaviour behaviour = new OutfitChangerBehaviour(priceList, 5 * 60, "Your mask has worn off.");
				new OutfitChangerAdder().addOutfitChanger(this, behaviour, "buy");
			}
		};

		npc.setEntityClass("woman_008_npc");
		npc.setPosition(x, y);
		npc.setDirection(Direction.DOWN);
		npc.initHP(100);
		npc.setDescription("You see a beautiful looking woman. Her name is Fidorea and she loooves colours.");
		zone.add(npc);
	}
}