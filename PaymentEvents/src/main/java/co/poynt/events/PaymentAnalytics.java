package co.poynt.events;

import android.content.Context;

import co.poynt.events.data.AnalyticsHelper;
import co.poynt.events.model.PFEvent;

import java.util.UUID;


public class PaymentAnalytics extends AnalyticsHelper {

    private static final String TAG = "PaymentAnalytics";

    private AnalyticsHelper mAnalyticsHelper;


    private static class LazyPaymentAnalyticsHolder {
        static final PaymentAnalytics INSTANCE = new PaymentAnalytics();
    }

    public static PaymentAnalytics getInstance() {
        return LazyPaymentAnalyticsHolder.INSTANCE;
    }

    public void initialize(Context context) {

        if (mAnalyticsHelper != null) {
            return;
        }

        mAnalyticsHelper = new AnalyticsHelper();
        mAnalyticsHelper.initializeDb(context);
    }

    public void logEvent(final PFEvent event, String sessionId, final String data) {
        if (sessionId == null) {
            sessionId = UUID.randomUUID().toString();
        }
        switch (event) {
            case INIT:
                mAnalyticsHelper.startSession(sessionId);
                break;

            case ACTION:
                mAnalyticsHelper.logTransactionAction(sessionId, data);
                break;

            case STATUS:
                mAnalyticsHelper.logTransactionStatus(sessionId, data);
                break;

            case RESULT:
                mAnalyticsHelper.logTransactionResult(sessionId, data);
                break;

            case UNKNOWN:
                break;
        }
    }

    public void logEvent(final PFEvent event, final String sessionId) {
        logEvent(event, sessionId, null);
    }

}
