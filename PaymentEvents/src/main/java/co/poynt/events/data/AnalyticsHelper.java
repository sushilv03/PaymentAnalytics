package co.poynt.events.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import co.poynt.events.dao.TransactionDatabase;
import co.poynt.events.model.Transaction;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AnalyticsHelper implements TransactionHelper {

    private static final String TAG = "AnalyticsHelper";

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String DATABASE_NAME = "transaction_analytics_db";
    private TransactionDatabase trxDatabase;

    public void initializeDb(Context context) {

        if (trxDatabase != null) {
            return;
        }
        trxDatabase = Room.databaseBuilder(context, TransactionDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    private TransactionDatabase getDatabase() {
        return trxDatabase;
    }


    public void startSession(@NonNull final String sessionId) {

        DisposableObserver<Transaction> saveTrx = createTransactionObj(sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(createNewSession());
        compositeDisposable.add(saveTrx);
    }

    @Override
    public Observable<Transaction> createTransactionObj(final String sessionId) {

        return Observable.create(new ObservableOnSubscribe<Transaction>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Transaction> emitter) {

                Transaction transaction = new Transaction();
                transaction.setSessionId(sessionId);
                transaction.setStartTimestamp();
                emitter.onNext(transaction);
            }
        });
    }

    public void logTransactionEvent(@NonNull final String sessionId, final String event) {

        DisposableObserver<Transaction> saveTrx = getTransactionEventObj(sessionId, event)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(updateEvent());
        compositeDisposable.add(saveTrx);
    }

    @Override
    public Observable<Transaction> getTransactionEventObj(final String sessionId,
                                                          final String event) {

        return Observable.create(new ObservableOnSubscribe<Transaction>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Transaction> emitter) {

                Transaction transaction = new Transaction();
                transaction.setSessionId(sessionId);
                transaction.setEvent(event);
                emitter.onNext(transaction);
            }
        });
    }

    public void logTransactionAction(String sessionId, final String action) {

        DisposableObserver<Transaction> startTrx = getTransactionActionObj(sessionId, action)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(updateAction());
        compositeDisposable.add(startTrx);
    }

    @Override
    public Observable<Transaction> getTransactionActionObj(final String sessionId,
                                                           final String action) {
        return Observable.create(new ObservableOnSubscribe<Transaction>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Transaction> emitter) {

                Transaction transaction = new Transaction();
                transaction.setSessionId(sessionId);
                transaction.setAction(action);
                emitter.onNext(transaction);
            }
        });
    }

    public void logTransactionStatus(String sessionId, String status) {

        DisposableObserver<Transaction> processTrx = getTransactionStatusObj(sessionId, status)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(updateStatus());
        compositeDisposable.add(processTrx);
    }

    @Override
    public Observable<Transaction> getTransactionStatusObj(final String sessionId,
                                                           final String status) {
        return Observable.create(new ObservableOnSubscribe<Transaction>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Transaction> emitter) {

                Transaction transaction = new Transaction();
                transaction.setSessionId(sessionId);
                transaction.setStatus(status);
                emitter.onNext(transaction);
            }
        });
    }

    public void logTransactionResult(String sessionId, String result) {

        DisposableObserver<Transaction> processTrx = getTransactionResultObj(sessionId, result)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(setResult());
        compositeDisposable.add(processTrx);
    }

    @Override
    public Observable<Transaction> getTransactionResultObj(final String sessionId,
                                                           final String result) {
        return Observable.create(new ObservableOnSubscribe<Transaction>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Transaction> emitter) {

                Transaction transaction = new Transaction();
                transaction.setSessionId(sessionId);
                transaction.setResult(result);
                transaction.setEndTimeStamp();
                emitter.onNext(transaction);
            }
        });
    }

    private DisposableObserver<Transaction> createNewSession() {
        return new DisposableObserver<Transaction>() {
            @Override
            public void onNext(Transaction response) {
                if (getDatabase() == null) {
                    return;
                }
                getDatabase().daoAccess().insertTransaction(response);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<Transaction> updateEvent() {
        return new DisposableObserver<Transaction>() {
            @Override
            public void onNext(Transaction response) {
                if (getDatabase() == null) {
                    return;
                }
                getDatabase().daoAccess().updatEvent(response.sessionId, response.event);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<Transaction> updateAction() {
        return new DisposableObserver<Transaction>() {
            @Override
            public void onNext(Transaction response) {
                if (getDatabase() == null) {
                    return;
                }
                getDatabase().daoAccess().updateAction(response.sessionId, response.action);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<Transaction> updateType() {
        return new DisposableObserver<Transaction>() {
            @Override
            public void onNext(Transaction response) {
                if (getDatabase() == null) {
                    return;
                }
                getDatabase().daoAccess().updateType(response.sessionId, response.trxType);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<Transaction> updateStatus() {
        return new DisposableObserver<Transaction>() {
            @Override
            public void onNext(Transaction response) {
                if (getDatabase() == null) {
                    return;
                }
                getDatabase().daoAccess().updateStatus(response.sessionId, response.status);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<Transaction> setResult() {
        return new DisposableObserver<Transaction>() {
            @Override
            public void onNext(Transaction response) {
                if (getDatabase() == null) {
                    return;
                }
                getDatabase().daoAccess().setResult(
                        response.sessionId,
                        response.result,
                        response.endTimeStamp);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

}
