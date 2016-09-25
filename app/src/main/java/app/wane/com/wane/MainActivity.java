package app.wane.com.wane;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.wane.com.dao.CatLastStatusSQLiteHelper;
import app.wane.com.model.User;
import app.wane.com.request.UserRequest;
import app.wane.com.soport.TokenRest;

public class MainActivity extends AppCompatActivity {

    private static final String logMainActivity = "MAIN_ACTIVITY";
    private static final String statusMessenger = "STATUS_MESSENGER";

    private View mProgressView;
    private Pedidos pedidos;
    private String[] datos;
    private ArrayAdapter<String> adaptador;
    private Spinner cmbOpciones;
    private Intent intent;

    private Spinner cmbStatusMessenger;
    private Button btnSaveStatusMessenger;
    private ChangeStatus changeStatus;
    private RelativeLayout contetStatusMessenger;
    private RelativeLayout contetFormMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        mProgressView = findViewById(R.id.activity_progress);
        cmbStatusMessenger = (Spinner) findViewById(R.id.cmbStatusMessenger);
        btnSaveStatusMessenger = (Button) findViewById(R.id.btnSaveStatusMessenger);
        contetStatusMessenger = (RelativeLayout) findViewById(R.id.content_status_messenger);
        contetFormMain = (RelativeLayout) findViewById(R.id.content_form_main);
        try {
            ObjectMapper mp = new ObjectMapper();
            UserRequest userRequest = new UserRequest("log-in", "request", "messenger", TokenRest.val, "log-in", new User("admin", "admin"));
            Log.i(logMainActivity, mp.writeValueAsString(userRequest));
        } catch (Exception e) {
            Log.e(logMainActivity, "ERROR to write json", e);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnSaveStatusMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showProgress(true);
                Log.i(statusMessenger, "change status");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.opt1) {
            Log.i(logMainActivity, "opt 1");
            showLayout(false, statusMessenger);
            showLayout(true, logMainActivity);
            //get pedidos
            showProgress(true);
            pedidos = new Pedidos();
            pedidos.execute((Void) null);
            return true;
        }else if(id == R.id.opt2){
            attemptStatusMesenger();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void attemptStatusMesenger(){
        showLayout(false, logMainActivity);
        //open conecction
        CatLastStatusSQLiteHelper sm = new CatLastStatusSQLiteHelper(MainActivity.this, null);
        changeStatus = new ChangeStatus();
        changeStatus.execute((Void) null);
        showLayout(true, statusMessenger);
    }

    public class Pedidos extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.i(logMainActivity, "get pedidos");
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            pedidos = null;
            showProgress(false);

            adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, datos);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbOpciones.setAdapter(adaptador);

        }

        @Override
        protected void onCancelled() {
            pedidos = null;
            showProgress(false);
        }
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
            String[] status = new String[]{"Disponible", "1 pedido asignado", "2 pedidos asignados", "3+ pedidos asignados",
                                            "Formado para pagar", "En camino a entrega", "Inactivo 10 minutos",
                                            "Inactivo 15 minutos", "Inactivo 20 minutos", "Inactivo 30+ minutos",
                                            "Accidente de tránsito", "Incidente con el usuario", "Desconectado"};
            //cmbStatusMessenger
            adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, status);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbStatusMessenger.setAdapter(adaptador);
            cmbStatusMessenger.setSelection(4);
        }

        @Override
        protected void onCancelled() {
            changeStatus = null;
        }
    }

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showLayout(final boolean show, final String nameLayout){
        if(nameLayout.equals(logMainActivity)){
            contetFormMain.setVisibility(show ? View.VISIBLE : View.GONE);
        }else if(nameLayout.equals(statusMessenger)){
            contetStatusMessenger.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

}
