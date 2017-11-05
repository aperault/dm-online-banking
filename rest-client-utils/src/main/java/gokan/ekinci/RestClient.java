package gokan.ekinci;

import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.Operation;
import gokan.ekinci.models.UserDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface RestClient {

    @GET("users")
    Call<List<UserDto>> users();

    @PUT("account/{id}")
    Call<AccountDto> updateBalance(@Path("id") String id,@Body Operation o);

    @GET("account/{id}")
    Call<AccountDto> getAccount(@Path("id") String id);

    @GET("users/{userId}/account")
    Call<List<AccountDto>> accounts(@Path("userId") String Id);

}
