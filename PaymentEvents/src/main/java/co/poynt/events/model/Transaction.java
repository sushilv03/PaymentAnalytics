package co.poynt.events.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import co.poynt.events.utils.TimeUtil;

@Entity (tableName = "transaction_analytics_db")
public class Transaction {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    public long id;

    @ColumnInfo(name = "session_id")
    public String sessionId;

    @ColumnInfo(name = "trx_event")
    public String event;

    @ColumnInfo(name = "trx_action")
    public String action;

    @ColumnInfo(name = "trx_type")
    public String trxType;

    @ColumnInfo(name = "trx_status")
    public String status;

    @ColumnInfo(name = "trx_result")
    public String result;

    @ColumnInfo(name = "more_info")
    public String moreInfo;

    @ColumnInfo(name = "start_timestamp")
    public String startTimestamp;

    @ColumnInfo(name = "end_timestamp")
    public String endTimeStamp;


    public Transaction() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp() {
        this.startTimestamp = TimeUtil.formatISO8601(new Date(System.currentTimeMillis()));
    }

    public String getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp() {
        this.endTimeStamp = TimeUtil.formatISO8601(new Date(System.currentTimeMillis()));
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
