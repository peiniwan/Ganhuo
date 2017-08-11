package ganhuo.ly.com.ganhuo.data.api;

import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import ganhuo.ly.com.ganhuo.mvp.entity.DayResults;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface GanHuoService {

    @GET("data/{type}/{number}/{page}")
    Observable<DataResults> getDataResults(
            @Path("type") String type,
            @Path("number") int number,
            @Path("page") int page
    );

    @GET("day/{year}/{month}/{day}")
    Observable<DayResults> getDayResults(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day
    );


}
