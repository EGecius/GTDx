package com.egecius.gtdx.shared.db

import com.google.firebase.database.DatabaseError

/**
 * Exception that occurred within [Db] implementation
 */
internal class DbException(private val databaseError: DatabaseError) : Exception() {

    override fun toString(): String {
        return "DbException{" +
                "databaseError=" + databaseError +
                '}'
    }
}
