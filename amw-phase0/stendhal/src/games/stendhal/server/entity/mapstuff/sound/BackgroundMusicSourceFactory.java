/*
 * @(#) src/games/stendhal/server/entity/area/CreatureProtectionAreaFactory.java
 *
 * $Id: BackgroundMusicSourceFactory.java,v 1.3 2013/04/25 19:48:56 kiheru Exp $
 */

package games.stendhal.server.entity.mapstuff.sound;

import games.stendhal.server.core.config.factory.ConfigurableFactoryContext;

/**
 * A factory for a BackgroundMusicSource.
 */
public class BackgroundMusicSourceFactory extends SoundSourceFactory {

	/**
	 * Create an BackgroundMusicSource.
	 * 
	 * @param ctx
	 *            Configuration context.
	 * 
	 * @return An BackgroundMusicSource.
	 * 
	 * @throws IllegalArgumentException
	 *             If there is a problem with the attributes.
	 * @see LoopedAmbientSoundSource
	 */
	@Override
	public BackgroundMusicSource create(final ConfigurableFactoryContext ctx) {
		BackgroundMusicSource source;

		source = new BackgroundMusicSource(getSound(ctx), getRadius(ctx), getVolume(ctx));

		return source;
	}
}
