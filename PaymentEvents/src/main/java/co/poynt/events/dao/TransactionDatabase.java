package co.poynt.events.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import co.poynt.events.model.Transaction;

@Database(entities = {Transaction.class}, version = 2, exportSchema = false)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDao daoAccess();
}
