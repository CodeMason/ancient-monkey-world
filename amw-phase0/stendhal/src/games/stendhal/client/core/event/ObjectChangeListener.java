/* $Id: ObjectChangeListener.java,v 1.3 2013/04/23 22:31:47 kiheru Exp $ */
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
package games.stendhal.client.core.event;

import marauroa.common.game.RPObject;

/**
 * is used by {@link PerceptionToObject}. 
 * 
 * Any Class implementing this can be used to listen to changes in RPObjects.
 * 
 * @author astrid
 *
 */
public interface ObjectChangeListener {
	void deleted();
	void modifiedAdded(RPObject changes);
	void modifiedDeleted(RPObject changes);
}
