package nyc.c4q.androidtest_unit4final.networking;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Wayne Kellman on 1/10/18.
 */

public interface ColorNetwork {

    @GET("css-color-names.json")
    Call<HashMap<String,String>> getColors();
}
