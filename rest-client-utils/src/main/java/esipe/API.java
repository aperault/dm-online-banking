package esipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class API {

    public static final String ENDPOINT_URL = "http://localhost:8181/data-access/";

    private static API instance;

    private RestClient restClient;


    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    public RestClient getRetrofitService() {
        if (restClient == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()
                            .registerModule(new ParameterNamesModule())
                            .registerModule(new Jdk8Module())
                            .registerModule(new JavaTimeModule())
                    ))
                    .build();
            restClient = retrofit.create(RestClient.class);
        }
        return restClient;
    }


}
