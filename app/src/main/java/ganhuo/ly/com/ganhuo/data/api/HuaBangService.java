package ganhuo.ly.com.ganhuo.data.api;

import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API接口
 */
public interface HuaBangService {

//    @FormUrlEncoded
//    @POST("/819-1/")
//    Observable<HuaResults> getDataResults(
//
//            @Field("showapi_appid") String showapi_appid,
//            @Field("showapi_sign") String showapi_sign,
//            @Field("type") String type,
//            @Field("num") String num,
//            @Field("page") String page
//
//    );

    @GET("favorite/{type}")
    Observable<HuaResults> getDataResults(@Path("type") String type, @Query("limit") int limit);

    //模板类型 的后续联网 max
    @GET("favorite/{type}")
    Observable<HuaResults> getDataResultsMax( @Path("type") String type, @Query("max") int max, @Query("limit") int limit);


}
