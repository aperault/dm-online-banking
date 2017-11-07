package gokan.ekinci;

import gokan.ekinci.models.AccountDto;
import gokan.ekinci.models.HistoryDto;
import gokan.ekinci.models.Operation;
import gokan.ekinci.models.UserDto;
import org.apache.catalina.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RestClient {

    @GET("users")
    Call<List<UserDto>> users();

    @GET("users/{id}")
    Call<UserDto> getUserById(@Path("id") String id);

    @GET("users/getby")
    Call<UserDto> getUserByLastName(@Query("lastname") String lastName);

    @POST("users")
    void createUser(@Body UserDto user);

    @PUT("users/{id}")
    void updateUser(@Path("id") String id,@Body UserDto userDto );

    @POST("users/{id]/account")
    void createAccount(@Path("id") String id,@Body AccountDto accountDto);

    @PUT("account/{id}")
    Call<AccountDto> updateBalance(@Path("id") String id,@Body Operation o);

    @GET("account/{id}")
    Call<AccountDto> getAccount(@Path("id") String id);

    @GET("account/{accountId}/history")
    Call<List<HistoryDto>> getHistoryByAccountId(@Path("accountId") String id) ;

    @GET("account/{accountId}/weeklyhistory")
    Call<List<HistoryDto>> getWeeklyHistory(@Path("accountId") String id) ;

    @GET("users/{userId}/account")
    Call<List<AccountDto>> accounts(@Path("userId") String Id);

}
