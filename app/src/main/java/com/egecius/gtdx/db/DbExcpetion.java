package com.egecius.gtdx.db;

import com.google.firebase.database.DatabaseError;

/**
 * Exception that occurred within {@link Db} implementation
 */
final class DbExcpetion extends Exception {

	private final DatabaseError databaseError;

	public DbExcpetion(final DatabaseError databaseError) {
		this.databaseError = databaseError;
	}

	@Override
	public String toString() {
		return "DbExcpetion{" +
				"databaseError=" + databaseError +
				'}';
	}
}
