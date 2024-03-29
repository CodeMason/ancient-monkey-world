/* $Id: TalkBuddyAction.java,v 1.7 2013/04/22 20:28:59 kiheru Exp $ */
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
package games.stendhal.client.gui.buddies;

import games.stendhal.client.gui.j2DClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TalkBuddyAction implements ActionListener {
	private final String buddyName;
	protected TalkBuddyAction(final String buddyName) {
		if (buddyName.indexOf(' ') > -1) {
			this.buddyName = "'" + buddyName + "'";
		} else {
			this.buddyName = buddyName;
		}

		
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		j2DClient.get().setChatLine("/tell " + buddyName + " ");
	}
}
