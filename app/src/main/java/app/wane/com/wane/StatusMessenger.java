package app.wane.com.wane;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class StatusMessenger extends Activity {

    private static final String statusMessenger = "STATUS_MESSENGER";
    private Spinner cmbStatusMessenger;
    private Button btnSaveStatusMessenger;
    private ChangeStatus changeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_messenger);
        cmbStatusMessenger = (Spinner) findViewById(R.id.cmbStatusMessenger);
        btnSaveStatusMessenger = (Button) findViewById(R.id.btnSaveStatusMessenger);
        btnSaveStatusMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus = new ChangeStatus();
                changeStatus.execute((Void) null);
            }
        });
    }
    public class ChangeStatus extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.i(statusMessenger, "requesting");
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            changeStatus = null;
            Log.i(statusMessenger, "row updated");
        }

        @Override
        protected void onCancelled() {
            changeStatus = null;
        }
    }
}
