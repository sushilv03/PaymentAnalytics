package co.poynt.events.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import co.poynt.events.model.Transaction;

@Dao
public interface TransactionDao {

    @Insert
    void insertTransaction(Transaction trx);

    @Query("UPDATE transaction_analytics_db SET trx_event= :event WHERE session_id= :sessionId")
    int updatEvent(String sessionId, String event);

    @Query("UPDATE transaction_analytics_db SET trx_action= :action WHERE session_id= :sessionId")
    int updateAction(String sessionId, String action);

    @Query("UPDATE transaction_analytics_db SET trx_type= :type WHERE session_id= :sessionId")
    int updateType(String sessionId, String type);

    @Query("UPDATE transaction_analytics_db SET trx_status= :status WHERE session_id= :sessionId")
    int updateStatus(String sessionId, String status);

    @Query("UPDATE transaction_analytics_db SET trx_result= :result, " +
            "end_timestamp= :endTimeStamp "+
            "WHERE session_id= :sessionId")
    int setResult(String sessionId, String result, String endTimeStamp);

    @Delete
    void deleteTransaction(Transaction trx);
}
