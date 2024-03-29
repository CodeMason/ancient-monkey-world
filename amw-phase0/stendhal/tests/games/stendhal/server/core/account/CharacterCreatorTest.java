/* $Id: CharacterCreatorTest.java,v 1.20 2013/04/26 22:27:07 kiheru Exp $ */
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
package games.stendhal.server.core.account;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import marauroa.common.Log4J;
import marauroa.common.game.Result;
import marauroa.server.db.DBTransaction;
import marauroa.server.db.TransactionPool;
import marauroa.server.game.db.DatabaseFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;

public class CharacterCreatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		new DatabaseFactory().initializeDatabase();
		PlayerTestHelper.generatePlayerRPClasses();
		ItemTestHelper.generateRPClasses();
	}

	/**
	 * Tests for create.
	 * @throws SQLException 
	 */
	@Test
	public void testCreate() throws SQLException {
		cleanDB();

		final CharacterCreator cc = new CharacterCreator("user", "player", null);
		assertEquals(Result.OK_CREATED, cc.create().getResult());
		assertEquals(Result.FAILED_PLAYER_EXISTS, cc.create().getResult());

		cleanDB();
	}

	private void cleanDB() throws SQLException {
		final DBTransaction transaction = TransactionPool.get().beginWork();
		try {
			transaction.execute("DELETE FROM character_stats where name='player';", null);
			transaction.execute("DELETE FROM rpobject WHERE object_id IN (SELECT object_id FROM characters WHERE characters.charname = 'player');", null);
			transaction.execute("DELETE FROM characters WHERE characters.charname = 'player';", null);
			TransactionPool.get().commit(transaction);
		} catch (final SQLException e) {
			TransactionPool.get().rollback(transaction);
			throw e;
		}
	}
}
