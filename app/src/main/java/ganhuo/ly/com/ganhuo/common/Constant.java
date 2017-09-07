package ganhuo.ly.com.ganhuo.common;

/**
 * 常量类
 * 本类存放项目所需常量
 */
public class Constant {

    public static final class Num {
        public static final int GH_NUM = 3;
        public static final int FI_NUM = 45;
        public static final int MZ_NUM = 45;
        public static final int READ_NUM = 20;
        public static final int PAGE_COUNT = 7;
    }

    public static final class Urls {
        public static final String API_SERVER="http://gank.io/api/";
        public static final String ZHIHU_DAILY_BEFORE = "http://news.at.zhihu.com";
        public static final String ZHIHU_DAILY_OFFLINE_NEWS = "http://news-at.zhihu.com";
        public static final String ZHIHU_DAILY_PURIFY_BEFORE = "http://zhihudailypurify.herokuapp.com/news/";
        public static final String SEARCH = "http://zhihudailypurify.herokuapp.com/search/";
        public static final String HUA_BANG_URL = "https://api.huaban.com";

    }


    public static final class Strings {
        public static final String ZHIHU_QUESTION_LINK_PREFIX = "http://www.zhihu.com/question/";
        public static final String SHARE_FROM_ZHIHU = " 分享自知乎网";
    }



    public static final class SharedPreferencesKeys {
        public static final String KEY_SHOULD_ENABLE_ACCELERATE_SERVER = "enable_accelerate_server?";
    }

    public static final class BundleKeys {
        public static final String DATE = "date";
        public static final String IS_SINGLE = "single?";
        public static final String IS_FIRST_PAGE = "first_page?";
    }


}
