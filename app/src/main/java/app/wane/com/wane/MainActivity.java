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
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import app.wane.com.adapters.AdapterPurchaseOrderDetails;
import app.wane.com.model.CatPurchaseOrder;
import app.wane.com.model.PurchaseOrder;
import app.wane.com.model.PurchaseOrderDetail;
import app.wane.com.response.ListCatPurchaseStatus;
import app.wane.com.response.ListPurchaseOrder;
import app.wane.com.response.ListPurchaseOrderDetail;
import app.wane.com.soport.HeaderRequest;
import app.wane.com.soport.HeaderResponse;
import app.wane.com.soport.TokenRest;

import static app.wane.com.soport.ApiService.requestHeaders;
import static app.wane.com.soport.ApiService.uriGetAllPurchaseOrder;
import static app.wane.com.soport.ApiService.uriGetAllPurchaseOrderDetail;
import static app.wane.com.soport.ApiService.uriLogout;
import static app.wane.com.soport.ApiService.uriPoStatusCatalog;

public class MainActivity extends AppCompatActivity {

    protected static final String logMainActivity = "MAIN_ACTIVITY";
    protected static final String statusMessenger = "statusMessenger";
    protected static final String logLogout = "logLogout";
    protected static final String logPurchaseOrder = "logPurchaseOrder";
    protected static final String logPurchaseOrderDetails = "logPurchaseOrderDetails";

    //components of view
    protected View mProgressView;
    protected Pedidos pedidos;
    protected String[] datos;
    protected ArrayAdapter<String> adaptador;
    protected Spinner cmbOpciones;
    protected Intent intent;
    protected ListView listaPurchase;
    protected ListView listaPurchaseDetails;

    protected Spinner cmbStatusMessenger;
    protected Button btnSaveStatusMessenger;
    protected RelativeLayout contetStatusMessenger;
    protected RelativeLayout contetFormMain;

    //catalog status of purchase
    protected String[] catalogStatusPurchase;

    //list of purchase
    protected List<PurchaseOrder> poList;

    //logout
    protected LogOut logOut;

    //object to change status of purchase
    protected ChangeStatus changeStatus;

    //rest service to get catalog status of purchase
    protected CatalogStatusPurchase statusPurchase;

    // other
    protected Toast msg;
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
        mProgressView = findViewById(R.id.activity_progress);
        cmbStatusMessenger = (Spinner) findViewById(R.id.cmbStatusMessenger);
        btnSaveStatusMessenger = (Button) findViewById(R.id.btnSaveStatusMessenger);
        contetStatusMessenger = (RelativeLayout) findViewById(R.id.content_status_messenger);
        contetFormMain = (RelativeLayout) findViewById(R.id.content_form_main);
        listaPurchase = (ListView) findViewById(R.id.listView);
        listaPurchaseDetails = (ListView) findViewById(R.id.listPurchaseDetails);


        //load by default catalog status of purchase
        statusPurchase = new CatalogStatusPurchase();
        statusPurchase.execute((Void) null);

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

        listaPurchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //hide purchase order
                showLayout(false, logMainActivity);
                //show progress circule
                showProgress(true);
                //load catalog of status
                attemptCatalogStatusMesenger();
                //setter a status of the purchase
                attemptStatusMesenger(position);
                //load details of purchase
                attemptDetailsPurchase(position);
                //hide progress circule
                showProgress(false);
                //show view details purchase
                showLayout(true, statusMessenger);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opt1) {
            Log.i(logMainActivity, "opt 1");
            //get pedidos
            showLayout(false, statusMessenger);
            showProgress(true);
            pedidos = new Pedidos();
            pedidos.execute((Void) null);
            showLayout(true, logMainActivity);
            return true;
        } else if (id == R.id.opt2) {
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

    public void attemptCatalogStatusMesenger() {

        statusPurchase = new CatalogStatusPurchase();
        statusPurchase.execute((Void) null);

    }

    public void attemptDetailsPurchase(int position){

        DetailsPurchase detailsPurchase = new DetailsPurchase();
        detailsPurchase.execute(position);

    }

    public void attemptStatusMesenger(int position){
        if(catalogStatusPurchase != null){
            //get rest service
            cmbStatusMessenger.setSelection(poList.get(position).getStatusid()+1);
        }
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

    public class DetailsPurchase extends AsyncTask<Integer, Void, Boolean> {

        List<PurchaseOrderDetail> poDetail;

        @Override
        protected Boolean doInBackground(Integer... params) {
            // rest service: request details purchase order
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            //HeaderRequest headerRequest = new HeaderRequest("podetail", String.valueOf(poList.get(params[0]).getPoid()));
            HeaderRequest headerRequest = new HeaderRequest("podetail", "otro");
            Log.i(logPurchaseOrderDetails, "object request to purchase details: " + headerRequest.toString());
            try {
                HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + mp.writeValueAsString(headerRequest), requestHeaders());
                ResponseEntity<String> response = restTemplate.exchange(uriGetAllPurchaseOrderDetail, HttpMethod.POST, requestEntity, String.class, requestEntity);
                Log.d(logPurchaseOrderDetails, "reponse of service purchase details: " + response.getBody());
                if (response.getStatusCode() != HttpStatus.OK) {
                    throw new HttpServerErrorException(response.getStatusCode());
                } else {
                    ListPurchaseOrderDetail listPurchaseOrderDetail = mp.readValue(response.getBody(), ListPurchaseOrderDetail.class);
                    if (listPurchaseOrderDetail.getResult().equals("success")) {
                        poDetail = listPurchaseOrderDetail.getPodetail().getArray();
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
                AdapterPurchaseOrderDetails adaptador = new AdapterPurchaseOrderDetails(MainActivity.this, poDetail);
                listaPurchaseDetails.setAdapter(adaptador);
            } else {
                msg = Toast.makeText(
                        getApplicationContext(),
                        "Error to load Purchase Order Details.",
                        Toast.LENGTH_LONG);
                msg.show();
            }
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
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
            } else {
                msg = Toast.makeText(
                        getApplicationContext(),
                        "Error to load Purchase Order.",
                        Toast.LENGTH_LONG);
                msg.show();
            }
            showProgress(false);
            pedidos = null;
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

    public class CatalogStatusPurchase extends AsyncTask<Void, Void, Boolean> {

        public List<CatPurchaseOrder> catalog;

        @Override
        protected Boolean doInBackground(Void... params) {

            if(catalogStatusPurchase == null){

                //request logout rest service
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                HeaderRequest headerRequest = new HeaderRequest("postatuscatalog");
                try {
                    HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + mp.writeValueAsString(headerRequest), requestHeaders());
                    ResponseEntity<String> response = restTemplate.exchange(uriPoStatusCatalog, HttpMethod.POST, requestEntity, String.class, requestEntity);
                    Log.d(statusMessenger, "reponse of service postatuscatalog" + response.getBody());
                    if (response.getStatusCode() != HttpStatus.OK) {
                        throw new HttpServerErrorException(response.getStatusCode());
                    } else {
                        ListCatPurchaseStatus listCatPurchaseStatus = mp.readValue(response.getBody(), ListCatPurchaseStatus.class);
                        if (listCatPurchaseStatus.getResult().equals("success")) {
                            catalog = listCatPurchaseStatus.getPostatuscatalog().getArray();
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

            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                catalogStatusPurchase = new String[catalog.size()];
                for (int i = 0; i < catalog.size(); i ++){
                    catalogStatusPurchase[i] =  catalog.get(i).getStatus();
                }
                adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, catalogStatusPurchase);
                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cmbStatusMessenger.setAdapter(adaptador);
            } else {

                if(catalogStatusPurchase == null){

                    msg = Toast.makeText(
                            getApplicationContext(),
                            "Error to load catalog of status to Purchase.",
                            Toast.LENGTH_LONG);
                    msg.show();

                } else {

                    Log.d(statusMessenger, "Catalog status of purchase, was load");

                }

            }
            statusPurchase = null;
            //cmbStatusMessenger.setSelection(4);
        }

        @Override
        protected void onCancelled() {
            statusPurchase = null;
        }
    }

    protected void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected void showLayout(final boolean show, final String nameLayout) {
        if (nameLayout.equals(logMainActivity)) {
            contetFormMain.setVisibility(show ? View.VISIBLE : View.GONE);
        } else if (nameLayout.equals(statusMessenger)) {
            contetStatusMessenger.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

}
