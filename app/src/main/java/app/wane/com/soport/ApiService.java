package app.wane.com.soport;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by Maricruz on 04/10/2016.
 */
public class ApiService {
    public final static String endPoint = "http://192.168.43.186/WaneRest/index.php/Service/";
    public final static String uriLogin = endPoint + "login";
    public final static HttpHeaders requestHeaders (){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return requestHeaders;
    }
}
