/* $Id: SoundSystemFacade.java,v 1.3 2013/05/12 18:19:19 kiheru Exp $ */
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
package games.stendhal.client.engine.sound.facade;

import games.stendhal.client.listener.PositionChangeListener;

import java.util.Collection;
import java.util.List;

/**
 * this class is the interface between the game logic and the
 * sound system.
 * 
 * @author hendrik, silvio
 */
public interface SoundSystemFacade extends PositionChangeListener {
	public void exit();

	public SoundGroup getGroup(String groupName);

	public void update();

	public void stop(SoundHandle sound, Time fadingDuration);

	public void mute(boolean turnOffSound, boolean useFading, Time delay);

	public float getVolume();

	public Collection<String> getGroupNames();

	public void changeVolume(float volume);

	public List<String> getDeviceNames();
}
