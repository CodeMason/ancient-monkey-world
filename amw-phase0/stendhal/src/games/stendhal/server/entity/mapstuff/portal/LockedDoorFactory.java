/*
 * @(#) src/games/stendhal/server/entity/portal/LockedDoorFactory.java
 *
 * $Id: LockedDoorFactory.java,v 1.5 2013/04/24 21:50:13 kiheru Exp $
 */

package games.stendhal.server.entity.mapstuff.portal;

//
//

import games.stendhal.server.core.config.factory.ConfigurableFactoryContext;

/**
 * A factory for <code>LockedDoor</code> objects.
 */
public class LockedDoorFactory extends DoorFactory {

	//
	// LockedDoorFactory
	//

	/**
	 * Extract the portal key from a context.
	 * 
	 * @param ctx
	 *            The configuration context.
	 * @return The key name.
	 * @throws IllegalArgumentException
	 *             If the class attribute is missing.
	 */
	protected String getKey(final ConfigurableFactoryContext ctx) {
		return ctx.getRequiredString("key");
	}

	//
	// ConfigurableFactory
	//

	/**
	 * Create a locked door.
	 * 
	 * @param ctx
	 *            Configuration context.
	 * 
	 * @return A LockedDoor.
	 * 
	 * @throws IllegalArgumentException
	 *             If there is a problem with the attributes. The exception
	 *             message should be a value suitable for meaningful user
	 *             interpretation.
	 * 
	 * @see LockedDoor
	 */
	@Override
	public Object create(final ConfigurableFactoryContext ctx) {
		return new LockedDoor(getKey(ctx), getClass(ctx));
	}
}
