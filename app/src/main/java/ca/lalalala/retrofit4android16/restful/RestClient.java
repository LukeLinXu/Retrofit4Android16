package ca.lalalala.retrofit4android16.restful;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by Luke Lin on 8/12/2015.
 */
public class RestClient {

    private static final String BASE_URL = "https://diandiangoal.com/api.php/";

    public interface RestfulService {
        @GET("merchants/1/10/4/43.8250873/-79.336804/")
        Call<JSONObject> getHistory();
    }

//    static OkHttpClient client = new OkHttpClient.Builder()
//            .sslSocketFactory(new NoSSLv3SocketFactory())
//            .hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            })
//            .build();

    static Retrofit retrofit = new Retrofit.Builder()
//            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(JsonConverterFactory.create())
            .build();

    public static RestfulService restfulService = retrofit.create(RestfulService.class);

}
