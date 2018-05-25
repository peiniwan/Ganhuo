package ganhuo.ly.com.ganhuo.data.api;

import ganhuo.ly.com.ganhuo.mvp.meizi.MeiziResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API接口
 */
public interface MeiziService {
    @GET("getlist/")
    Observable<MeiziResult> getTitlelist(@Query("limit") String limit, @Query("offset") String offset);

    @GET("getlistpic/")
    Observable<MeiziResult> getlistpic(@Query("mid") String mid, @Query("limit") String limit, @Query("offset") String offset);

}
