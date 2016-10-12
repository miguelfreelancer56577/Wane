package app.wane.com.wane;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import app.wane.com.adapters.AdapterPurchaseOrderDetails;
import app.wane.com.model.CatPurchaseOrder;
import app.wane.com.model.PurchaseOrderDetail;
import app.wane.com.response.ListCatPurchaseStatus;
import app.wane.com.response.ListPurchaseOrderDetail;
import app.wane.com.soport.HeaderRequest;
import app.wane.com.soport.HeaderResponse;

import static app.wane.com.soport.ApiService.requestHeaders;
import static app.wane.com.soport.ApiService.uriGetAllPurchaseOrderDetail;
import static app.wane.com.soport.ApiService.uriPoStatusCatalog;
import static app.wane.com.soport.ApiService.uriUpdatePoStatus;

public class PurchaseOrderDetails extends AppCompatActivity {

    //logs
    protected static final String statusMessenger = "statusMessenger";
    protected static final String logPurchaseOrderDetails = "logPurchaseOrderDetails";
    protected static final String logChangeStatusPurchase = "logChangeStatusPurchase";

    //api to rest service
    protected RestTemplate restTemplate = new RestTemplate();
    //object to mapper json
    ObjectMapper mp = new ObjectMapper();
    //service
    PoStatusCatalog poStatusCatalog;
    DetailsPurchase detailsPurchase;
    //adapters
    protected ArrayAdapter<String> adaptador;
    //catalog of status
    protected List<CatPurchaseOrder> catalog;
    //components
    protected Spinner cmbStatusMessenger;
    protected Button btnSaveStatusMessenger;
    protected ListView listaPurchaseDetails;
    protected ProgressBar mProgressView;
    protected Toast msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get components
        cmbStatusMessenger = (Spinner) findViewById(R.id.cmbStatusMessenger);
        btnSaveStatusMessenger = (Button) findViewById(R.id.btnSaveStatusMessenger);
        listaPurchaseDetails = (ListView) findViewById(R.id.listPurchaseDetails);
        mProgressView = (ProgressBar) findViewById(R.id.activity_progress);

        //events
        btnSaveStatusMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSaveStatusMessenger.setEnabled(false);
                String selected =  cmbStatusMessenger.getSelectedItem().toString();
                for (CatPurchaseOrder item: catalog){
                    if(item.getStatus().equals(selected)){
                        int statusId = item.getStatusid();
                        new ChangeStatusPurchase().execute(statusId);
                        new PoStatusCatalog(statusId).execute((Void) null);
                        break;
                    }
                }
                btnSaveStatusMessenger.setEnabled(true);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        //get purchase details
        Bundle bundle = getIntent().getExtras();
        int[] params = bundle.getIntArray("params");

        //load details of purchase
        if(detailsPurchase == null){

            detailsPurchase = new DetailsPurchase();
            detailsPurchase.execute(params[0]);

        }

        // get postatuscatalog
        if(poStatusCatalog == null){

            poStatusCatalog = new PoStatusCatalog(params[1]);
            poStatusCatalog.execute((Void) null);

        }

    }

    public class PoStatusCatalog extends AsyncTask<Void, Void, Boolean> {

        protected int selectedStatusId;

        public PoStatusCatalog(int selectedStatusId) {
            this.selectedStatusId = selectedStatusId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            if(catalog == null){

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

            }else{
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {

                for (int i = 0; i < catalog.size(); i ++){
                    if(catalog.get(i).getStatusid() == selectedStatusId){
                        int size = catalog.size() - ( i + 1 );
                        String[] items  = new String[size];
                        for (int j = 0; j < size; j ++){
                            items[j] =  catalog.get((j + i)).getStatus();
                        }
                        adaptador = new ArrayAdapter<String>(PurchaseOrderDetails.this, android.R.layout.simple_spinner_item, items);
                        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        cmbStatusMessenger.setAdapter(adaptador);
                        cmbStatusMessenger.setSelection(0);
                        break;
                    }
                }
            } else {

                msg = Toast.makeText(
                        getApplicationContext(),
                        "Error to load catalog of status to Purchase.",
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

    public class DetailsPurchase extends AsyncTask<Integer, Void, Boolean> {

        List<PurchaseOrderDetail> poDetail;

        @Override
        protected Boolean doInBackground(Integer... params) {
            // rest service: request details purchase order
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            //HeaderRequest headerRequest = new HeaderRequest("podetail", String.valueOf(poList.get(params[0]).getPoid()));
            HeaderRequest headerRequest = new HeaderRequest("podetail", params[0].toString());
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
                AdapterPurchaseOrderDetails adaptador = new AdapterPurchaseOrderDetails(PurchaseOrderDetails.this, poDetail);
                listaPurchaseDetails.setAdapter(adaptador);
            } else {
                msg = Toast.makeText(
                        getApplicationContext(),
                        "Error to load Purchase Order Details.",
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

    public class ChangeStatusPurchase extends AsyncTask<Integer, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Integer... params) {
            //request logout rest service
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            HeaderRequest headerRequest = new HeaderRequest("updatepostatus", params[0].toString());
            try {
                HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + mp.writeValueAsString(headerRequest), requestHeaders());
                ResponseEntity<String> response = restTemplate.exchange(uriUpdatePoStatus, HttpMethod.POST, requestEntity, String.class, requestEntity);
                Log.d(logChangeStatusPurchase, "reponse of service changeStatusPurchase" + response.getBody());
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
                msg = Toast.makeText(PurchaseOrderDetails.this, "Purchase Updated Successfuly", Toast.LENGTH_LONG);
            } else {
                msg = Toast.makeText(PurchaseOrderDetails.this, "ERROR to update status", Toast.LENGTH_LONG);
            }
            msg.show();
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

    protected void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
