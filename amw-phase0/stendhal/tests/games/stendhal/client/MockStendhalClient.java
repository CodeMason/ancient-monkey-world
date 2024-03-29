/* $Id: MockStendhalClient.java,v 1.6 2010/12/28 14:00:00 martinfuchs Exp $ */
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
package games.stendhal.client;

import games.stendhal.client.core.event.PerceptionDispatcher;

public class MockStendhalClient extends StendhalClient {

	protected MockStendhalClient() {
		super(new UserContext(), new PerceptionDispatcher());
		client = this;
	}

}
