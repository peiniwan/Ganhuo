package ganhuo.ly.com.ganhuo.data.api;

import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * API接口
 */
public interface HuaBangService {

    @FormUrlEncoded
    @POST("/819-1/")
    Observable<HuaResults> getDataResults(

            @Field("showapi_appid") String showapi_appid,
            @Field("showapi_sign") String showapi_sign,
            @Field("type") String type,
            @Field("num") String num,
            @Field("page") String page

    );


}
