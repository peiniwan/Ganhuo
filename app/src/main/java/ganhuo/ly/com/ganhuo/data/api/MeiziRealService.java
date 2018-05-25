package ganhuo.ly.com.ganhuo.data.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * API接口
 */
public interface MeiziRealService {

    @GET
    Call<ResponseBody> getRealpic(@Header("User-Agent") String user_agent, @Header("Referer") String referer, @Url String url);
}
