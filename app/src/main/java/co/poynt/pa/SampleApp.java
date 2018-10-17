package co.poynt.pa;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
//import co.poynt.events.PaymentAnalytics;
//import co.poynt.events.model.PFEvent;

import java.util.UUID;


public class SampleApp extends AppCompatActivity {

    private String mSessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        PaymentAnalytics.getInstance().initialize(this);
        mSessionId = UUID.randomUUID().toString();
        PaymentAnalytics.getInstance().logEvent(PFEvent.INIT, mSessionId);
        SystemClock.sleep(100);
        logTransactionAction(mSessionId, "ACTION");
        SystemClock.sleep(100);
        logTransactionStatus(mSessionId, "STATUS");
        SystemClock.sleep(100);
        logTransactionComplete(mSessionId, "COMPLETE");
        */
    }
/*
    private void logTransactionAction(final String sessionId, String action) {
        PaymentAnalytics.getInstance().logEvent(PFEvent.ACTION, sessionId, action);
    }

    private void logTransactionStatus(final String sessionId, String status) {
        PaymentAnalytics.getInstance().logEvent(PFEvent.STATUS, sessionId, status);
    }

    private void logTransactionComplete(final String sessionId, String status) {
        PaymentAnalytics.getInstance().logEvent(PFEvent.RESULT, sessionId, status);
    }

    private void performUnknownAction(String sessionId) {
        PaymentAnalytics.getInstance().logEvent(PFEvent.UNKNOWN, sessionId);
    }
*/

}