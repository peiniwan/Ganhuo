package ganhuo.ly.com.ganhuo.mvp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class HuaResults {


    /**
     * showapi_res_code : 0
     * showapi_res_body : {"0":{"title":"一只热爱摇滚的2.5次元秋田核犬求拖走\u2026","thumb":"http://zzssa.b0.upaiyun.com/Uploads/2016-11-14/0060lm7Tgw1f9qbvupqbjj30dw0ku42b.jpg","url":"http://www.hbmeinv.com/show-35-33766-1.html"}}
     */

    private int showapi_res_code;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * 0 : {"title":"一只热爱摇滚的2.5次元秋田核犬求拖走\u2026","thumb":"http://zzssa.b0.upaiyun.com/Uploads/2016-11-14/0060lm7Tgw1f9qbvupqbjj30dw0ku42b.jpg","url":"http://www.hbmeinv.com/show-35-33766-1.html"}
         */

        @SerializedName("0")
        private _$0Bean _$0;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public static class _$0Bean {
            /**
             * title : 一只热爱摇滚的2.5次元秋田核犬求拖走…
             * thumb : http://zzssa.b0.upaiyun.com/Uploads/2016-11-14/0060lm7Tgw1f9qbvupqbjj30dw0ku42b.jpg
             * url : http://www.hbmeinv.com/show-35-33766-1.html
             */

            private String title;
            private String thumb;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
