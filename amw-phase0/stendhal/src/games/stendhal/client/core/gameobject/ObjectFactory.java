/* $Id: ObjectFactory.java,v 1.6 2013/04/23 22:31:47 kiheru Exp $ */
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
package games.stendhal.client.core.gameobject;

import games.stendhal.client.core.event.ObjectChangeListenerAdapter;
import games.stendhal.client.StendhalClient;
import games.stendhal.client.core.UserController;
import marauroa.common.game.RPObject;

public class ObjectFactory {

	private static final ObjectChangeListenerAdapter LISTENER = new ObjectChangeListenerAdapter();
/**
 * This is called when an object is added to a zone.
 * decide which listener is to be put to the added object. this is ugly. any ideas welcome (durkham).
 * @param object
 * @param perceptionTobject
 */
	void onAdded(final RPObject object,
			final PerceptionToObject perceptionTobject) {
		if ("player".equals(object.getRPClass().getName())) {
			if (StendhalClient.client.isUser(object)) {
				perceptionTobject.register(object, new UserController());
			}
		}

			perceptionTobject.register(object, LISTENER);

	}

}
