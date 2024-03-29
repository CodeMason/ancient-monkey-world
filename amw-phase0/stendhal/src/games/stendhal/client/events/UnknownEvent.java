/* $Id: UnknownEvent.java,v 1.5 2013/04/23 15:17:14 kiheru Exp $ */
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
package games.stendhal.client.events;

import games.stendhal.client.entity.Entity;

import org.apache.log4j.Logger;

/**
 * an unknown event
 *
 * @author hendrik
 * @param <T> entity
 */
class UnknownEvent<T extends Entity> extends Event<T> {
	private static Logger logger = Logger.getLogger(UnknownEvent.class);

	@Override
	public void execute() {
		logger.warn("Received unknown event: " + event + " on entity " + entity);
	}
}
