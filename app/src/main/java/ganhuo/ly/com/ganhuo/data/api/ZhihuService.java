package ganhuo.ly.com.ganhuo.data.api;

import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuDetailResults;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * API接口
 *
 */
public interface ZhihuService {

    @GET("/api/4/news/before/{timestamp}")
    Observable<ZhiHuResults> getDataResults(
            @Path("timestamp") String timestamp
    );

    @GET("/api/4/news/{id}")
    Observable<ZhiHuDetailResults> getDataResultsDetail(
            @Path("id") String id
    );



}
