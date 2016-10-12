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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import app.wane.com.adapters.AdapterPurchaseOrder;
import app.wane.com.model.PurchaseOrder;
import app.wane.com.response.ListPurchaseOrder;
import app.wane.com.soport.HeaderRequest;
import app.wane.com.soport.HeaderResponse;
import app.wane.com.soport.TokenRest;

import static app.wane.com.soport.ApiService.requestHeaders;
import static app.wane.com.soport.ApiService.uriGetAllPurchaseOrder;
import static app.wane.com.soport.ApiService.uriLogout;

public class MainActivity extends AppCompatActivity {

    //logs
    protected static final String logMainActivity = "MAIN_ACTIVITY";
    protected static final String logLogout = "logLogout";
    protected static final String logPurchaseOrder = "logPurchaseOrder";

    //components of view
    protected View mProgressView;
    protected Spinner cmbOpciones;
    protected ListView listaPurchase;
    protected RelativeLayout contetFormMain;
    protected Toast msg;

    // other
    protected Intent intent;

    //service
    protected Pedidos pedidos;
    protected LogOut logOut;

    //list of purchase
    protected List<PurchaseOrder> poList;
    //api to rest service
    protected RestTemplate restTemplate = new RestTemplate();
    //object to mapper json
    ObjectMapper mp = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get components
        mProgressView = findViewById(R.id.activity_progress);
        contetFormMain = (RelativeLayout) findViewById(R.id.content_form_main);
        listaPurchase = (ListView) findViewById(R.id.listView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listaPurchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, PurchaseOrderDetails.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("params", new String[]{Integer.toString(poList.get(position).getPoid()), Integer.toString(poList.get(position).getStatusid()), poList.get(position).getMapurl()});
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        //load and display purchase orders
        if(pedidos == null){
            pedidos = new Pedidos();
            pedidos.execute((Void) null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opt1) {
            new Pedidos().execute((Void) null);
            return true;
        } else if (id == R.id.opt3) {
            logOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logOut() {
        logOut = new LogOut();
        logOut.execute((Void) null);
    }

    public class LogOut extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            //request logout rest service
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            HeaderRequest headerRequest = new HeaderRequest("logout");
            try {
                HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + mp.writeValueAsString(headerRequest), requestHeaders());
                ResponseEntity<String> response = restTemplate.exchange(uriLogout, HttpMethod.POST, requestEntity, String.class, requestEntity);
                Log.d(logLogout, "reponse of service json" + response.getBody());
                if (response.getStatusCode() != HttpStatus.OK) {
                    throw new HttpServerErrorException(response.getStatusCode());
                } else {
                    HeaderResponse headerResponse = mp.readValue(response.getBody(), HeaderResponse.class);
                    if (headerResponse.getResult().equals("success")) {
                        return true;
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                TokenRest.val = "0";
                finish();
            } else {
                msg = Toast.makeText(MainActivity.this, "ERROR to logout", Toast.LENGTH_LONG);
                msg.show();
            }
        }

        @Override
        protected void onCancelled() {
            logOut = null;
        }
    }

    public class Pedidos extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // rest service: request get all purchase order
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            HeaderRequest headerRequest = new HeaderRequest("po");
            Log.i(logPurchaseOrder, "object request: " + headerRequest.toString());
            try {
                HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + mp.writeValueAsString(headerRequest), requestHeaders());
                ResponseEntity<String> response = restTemplate.exchange(uriGetAllPurchaseOrder, HttpMethod.POST, requestEntity, String.class, requestEntity);
                Log.d(logPurchaseOrder, "reponse of service json" + response.getBody());
                if (response.getStatusCode() != HttpStatus.OK) {
                    throw new HttpServerErrorException(response.getStatusCode());
                } else {
                    ListPurchaseOrder listPurchaseOrder = mp.readValue(response.getBody(), ListPurchaseOrder.class);
                    if (listPurchaseOrder.getResult().equals("success")) {
                        poList = listPurchaseOrder.getPolist().getListPoList();
                        return true;
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                AdapterPurchaseOrder adaptador = new AdapterPurchaseOrder(MainActivity.this, poList);
                listaPurchase.setAdapter(adaptador);
                msg = Toast.makeText(MainActivity.this, "Records updated", Toast.LENGTH_SHORT);
                msg.show();
            } else {
                msg = Toast.makeText(
                        getApplicationContext(),
                        "Error to load Purchase Order.",
                        Toast.LENGTH_LONG);
                msg.show();
            }
        }

        @Override
        protected void onCancelled() {
            msg = Toast.makeText(
                    getApplicationContext(),
                    "Request canceled.",
                    Toast.LENGTH_LONG);
            msg.show();
        }
    }

    protected void showLayout(final boolean show, final String nameLayout) {
        if (nameLayout.equals(logMainActivity)) {
            contetFormMain.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

}
