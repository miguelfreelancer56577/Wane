package app.wane.com.soport;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

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

import app.wane.com.request.LocationReport;

import static app.wane.com.soport.ApiService.requestHeaders;
import static app.wane.com.soport.ApiService.uriLocationReport;

public class Tracking extends GPSTracker{

	protected RestTemplate restTemplate = new RestTemplate();;
	ObjectMapper mp = new ObjectMapper();
	protected String logTracking = "logTracking";


	/**
	 * call super constructor
	 * @param context
	 */

	public Tracking(Context context, long minDistance, long minTime) {
		super(context, minDistance, minTime);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get a Location changed
	 */
	@Override
	public void onLocationChanged(Location location) {
		getLocation(location);
	}

	@Override
	public void getLocation(Location location) {
		if (location != null) {
			new LocationReportService().execute(
					String.valueOf(location.getLatitude()),
					String.valueOf(location.getLongitude())
			);
		}
	}
	
	/**
	 * Provider of gps disabled
	 */
	@Override
	public void onProviderDisabled(String provider){
		Log.d(logTracking,"Provider OFF");
	}
	/**
	 * Provider of gps enabled
	 */
	@Override
	public void onProviderEnabled(String provider){
		Log.d(logTracking,"Provider ON");
	}
	/**
	 * Listening on status changed
	 */
	@Override
	public void onStatusChanged(String provider, int status,Bundle extras){
		Log.i(logTracking, "Provider Status: " + status);
	}

	public class LocationReportService extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			//request logout rest service
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			LocationReport locationReport = new LocationReport("locationreport", params[0], params[1]);
			try {
				HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + mp.writeValueAsString(locationReport), requestHeaders());
				ResponseEntity<String> response = restTemplate.exchange(uriLocationReport, HttpMethod.POST, requestEntity, String.class, requestEntity);
				Log.d(logTracking, "reponse of service location report: " + response.getBody());
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
				Log.d(logTracking, "Location saved successfully");
			} else {
				Log.d(logTracking, "Error to save location");
			}
		}

		@Override
		protected void onCancelled() {

		}

	}
	
}
