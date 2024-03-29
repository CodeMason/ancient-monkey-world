/* $Id: SpriteStore.java,v 1.35 2013/04/23 22:31:52 kiheru Exp $ */
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
package games.stendhal.client.engine.graphics.sprite;


import games.stendhal.client.core.IGameScreen;
import games.stendhal.client.gui.j2d.Blend;
import games.stendhal.client.engine.graphics.sprite.TileSprite.TSRef;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * A resource manager for sprites in the game. Its often quite important how and
 * where you get your game resources from. In most cases it makes sense to have
 * a central resource loader that goes away, gets your resources and caches them
 * for future use.
 * <p>
 * [singleton]
 * <p>
 */
public class SpriteStore {
	/** the logger instance. */
	private static final Logger logger = Logger.getLogger(SpriteStore.class);

	/** The single instance of this class. */
	private static SpriteStore single = new SpriteStore();

	/**
	 * Screen graphics configuration.
	 */
	private GraphicsConfiguration gc;

	protected SpriteStore() {
		try {
			gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		} catch (HeadlessException e) {
			logger.warn("Cannot initialize graphic user interface: " + e);
		}
	}

	/**
	 * Get the single instance of this class.
	 * 
	 * @return The single instance of this class
	 */
	public static SpriteStore get() {
		return single;
	}

	/**
	 * Create an animated sprite from a tile sprite using pixel units.
	 * 
	 * @param sprite
	 *            The image which contains the different frames.
	 * @param x
	 *            The base X coordinate (in pixels).
	 * @param y
	 *            The base Y coordinate (in pixels).
	 * @param frameCount
	 *            The number of frames in this animation.
	 * @param width
	 *            The tile width (in pixels).
	 * @param height
	 *            The tile height (in pixels).
	 * @param delay
	 *            The minimum delay between frames.
	 * 
	 * @return An animated sprite.
	 */
	public AnimatedSprite getAnimatedSprite(final Sprite sprite, final int x,
			final int y, final int frameCount, final int width,
			final int height, final int delay) {
		return new AnimatedSprite(getTiles(sprite, x, y, frameCount, width,
				height), delay, true);
	}

	/**
	 * Get sprite tiles from a sprite using pixel units.
	 * 
	 * @param sprite
	 *            The base image.
	 * @param x
	 *            The base X coordinate (in pixels).
	 * @param y
	 *            The base Y coordinate (in pixels).
	 * @param count
	 *            The number of tiles.
	 * @param width
	 *            The tile width (in pixels).
	 * @param height
	 *            The tile height (in pixels).
	 * 
	 * @return An array of sprites.
	 */
	public Sprite[] getTiles(final Sprite sprite, final int x, final int y,
			final int count, final int width, final int height) {
		if (sprite == null) {
			return new Sprite[0];
		}

		final Sprite[] sprites = new Sprite[count];

		int tx = x;

		for (int i = 0; i < count; i++) {
			sprites[i] = getTile(sprite, tx, y, width, height);
			tx += width;
		}

		return sprites;
	}

	private static final String FAILSAFE_ICON_REF = "data/sprites/failsafe.png";

	/**
	 * Get the failsafe sprite. 
	 * The failsafe sprite is needed in case there are newer graphic requested in server than in client.
	 * 
	 * It is ok not to return a handmade one. 
	 * If we cannot reach the failsafe icon,
	 * we have bigger problems then just the need to show anything.
	 * 
	 * @return The failsafe sprite.
	 */
	public Sprite getFailsafe() {
		return getSprite(FAILSAFE_ICON_REF);
	}

	/**
	 * Retrieve a sprite from the store.
	 * 
	 * @param ref
	 *            The reference to the image to use for the sprite
	 * @return A sprite instance containing an accelerate image of the request
	 *         reference
	 */
	public Sprite getSprite(final String ref) {
		final SpriteCache cache = SpriteCache.get();

		Sprite sprite = cache.get(ref);

		if (sprite == null) {

			sprite = loadSprite(ref);
			if (sprite != null) {
				cache.add(ref, sprite);
			}
		}

		return sprite;
	}
	 
	/**
	 * Get a colored version of a sprite.
	 * 
	 * @param ref base sprite reference
	 * @param color painting color
	 * @return base sprite colored with color
	 */
	public Sprite getColoredSprite(final String ref, final Color color) {
		if (color == null) {
			return getSprite(ref);
		} else {
			return getModifiedSprite(ref, color, Blend.TrueColor);
		}
	}
	
	/**
	 * Get a modified version of a sprite.
	 * 
	 * @param baseRef base sprite reference
	 * @param color modifying color
	 * @param blend composite mode to paint color over the original sprite
	 * @return base sprite colored with color
	 */
	public Sprite getModifiedSprite(final String baseRef, final Color color, 
			final Composite blend) {
		if ((color == null) || (blend == null)) {
			return getSprite(baseRef);
		}
		
		final SpriteCache cache = SpriteCache.get();

		String realRef = createModifiedRef(baseRef, color, blend);
		Sprite sprite = cache.get(realRef);
		if (sprite == null) {
			sprite = modifySprite(getSprite(baseRef), color, blend, realRef);
		}

		return sprite;
	}
	
	/**
	 * Get a reference string for a modified sprite.
	 * 
	 * @param baseRef reference for the base sprite
	 * @param color
	 * @param blend
	 * @return reference string
	 */
	public String createModifiedRef(String baseRef, Color color, Composite blend) {
		String colorName = Integer.toHexString(color.getRGB());
		return baseRef + "@" + blend.toString() + "#" + colorName;
	}
	
	/**
	 * Get a modified variant of a sprite. The existence of a previous instance
	 * is <b>not</b> checked, so this should not be called unless retrieving
	 * an existing modified sprite has failed.
	 * 
	 * @param base original sprite
	 * @param color adjustment color
	 * @param blend blend mode for applying the adjustment color
	 * @param ref reference for the new sprite
	 * @return modified sprite
	 */
	public Sprite modifySprite(Sprite base, Color color, Composite blend, Object ref) {
		int width = base.getWidth();
		int height = base.getHeight();
		BufferedImage image = gc.createCompatibleImage(width, height,
				Transparency.BITMASK);
		Graphics2D g = image.createGraphics();
		base.draw(g, 0, 0);
		g.setColor(color);
		g.setComposite(blend);
		g.fillRect(0, 0, width, height);
		g.dispose();
		
		Sprite sprite = new ImageSprite(image, ref);
		SpriteCache cache = SpriteCache.get();
		cache.add(ref, sprite);
		
		return sprite;
	}

	/**
	 * Checks if a file exists.
	 * 
	 * @param ref
	 *            the file name
	 * @return if sprite exists in store false otherwise
	 */
	public boolean existsSprite(final String ref) {
		final URL url = DataLoader.getResource(ref);
		return url != null;
	}

	/**
	 * Load a sprite from a resource reference.
	 * 
	 * @param ref
	 *            The image resource name.
	 * 
	 * @return A sprite, or <code>null</code> if missing/on error.
	 */
	private Sprite loadSprite(final String ref) {
		BufferedImage sourceImage = null;

		try {
			URL url;
			if (ref.startsWith("http://")) {
				logger.info("Loading sprite from a URL...");
				url = new URL(ref);
			} else {
				url = DataLoader.getResource(ref);
			}
			if (url == null) {
				logger.error("Can't find ref: " + ref);

				// avoid infinite loop and stack overflow in case of missing
				// failsafe icon
				if (ref.equals(FAILSAFE_ICON_REF)) {
					logger.error("Cannot find FAILSAFE_ICON_REF: " + FAILSAFE_ICON_REF);
					return null;
				} else {
					return getFailsafe();
				}
			}

			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (final IOException e) {
			logger.error("Failed to read: " + ref, e);

			if (ref.equals(FAILSAFE_ICON_REF)) {
				logger.error("Cannot read FAILSAFE_ICON_REF: " + FAILSAFE_ICON_REF);
				return null;
			} else {
				return getFailsafe();
			}
		}

		// create an accelerated image of the right size to store our sprite in
		final int mode = Transparency.BITMASK;

		// ALPHA channel makes it runs 30% slower.
		// mode=Transparency.TRANSLUCENT;

		final Image image = gc.createCompatibleImage(sourceImage.getWidth(),
				sourceImage.getHeight(), mode);

		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage, 0, 0, null);

		// create a sprite, add it the cache then return it
		final Sprite sprite = new ImageSprite(image, ref);

		return sprite;
	}

	/**
	 * Get an empty sprite with the size of a single tile.
	 * 
	 * @return An empty sprite.
	 */
	public Sprite getEmptySprite() {
		return getEmptySprite(IGameScreen.SIZE_UNIT_PIXELS,
				IGameScreen.SIZE_UNIT_PIXELS);
	}

	/**
	 * Get an empty sprite.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * 
	 * @return An empty sprite.
	 */
	public Sprite getEmptySprite(final int width, final int height) {
		final SpriteCache cache = SpriteCache.get();

		final Object reference = EmptySprite.createReference(width, height);

		Sprite sprite = cache.get(reference);

		if (sprite == null) {
			sprite = new EmptySprite(width, height, reference);
			cache.add(reference, sprite);
		}

		return sprite;
	}

	/**
	 * Create a sprite tile (sub-region).
	 * @param sprite 
	 * @param x 
	 * @param y 
	 * 
	 * 
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @return tile found in cache or new one created from sprite
	 */
	public Sprite getTile(final Sprite sprite, final int x, final int y, final int width, final int height) {
		final SpriteCache cache = SpriteCache.get();

		final TSRef reference = TileSprite.createReference(sprite, x, y, width,
				height);

		Sprite tile = cache.get(reference);

		if (tile == null) {
			tile = sprite.createRegion(x, y, width, height, reference);
			// tile = new TileSprite(sprite, x, y, width, height, reference);

			if (reference != null) {
				cache.add(reference, tile);
			}
		}

		return tile;
	}

}
