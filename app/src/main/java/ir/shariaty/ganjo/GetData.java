package ir.shariaty.ganjo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {
    String BASE_URL = "https://ganjgah.ir/api/ganjoor/hafez/";
    @GET("Faal")
    Call<Faal> getFal();
}