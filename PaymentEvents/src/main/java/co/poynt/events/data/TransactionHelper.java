package co.poynt.events.data;

import co.poynt.events.model.Transaction;
import io.reactivex.Observable;

public interface TransactionHelper {

    //region Add to Database
    Observable<Transaction> createTransactionObj(final String sessionId);

    Observable<Transaction> getTransactionEventObj(final String sessionId, final String event);

    Observable<Transaction> getTransactionActionObj(final String sessionId, final String action);

    Observable<Transaction> getTransactionStatusObj(final String sessionId, final String status);

    Observable<Transaction> getTransactionResultObj(final String sessionId, final String result);
    //endregion

    //region Fetch from Database
    Observable<Integer> getTotalStartEvents(final String event);

    Observable<Integer> getTotalEventsForAction(final String action);

    Observable<Integer> getTotalEventsForStatus(final String status);

    Observable<Integer> getTotalCompletedEvents(final String result);

    //endregion
}
