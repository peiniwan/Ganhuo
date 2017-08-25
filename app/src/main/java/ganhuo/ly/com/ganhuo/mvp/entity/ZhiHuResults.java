package ganhuo.ly.com.ganhuo.mvp.entity;

import java.util.List;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class ZhiHuResults {

    /**
     * date : 20170822
     * stories : [{"images":["https://pic2.zhimg.com/v2-ec8026acb9d8a577f10d95722558c7dd.jpg"],"type":0,"id":9582980,"ga_prefix":"082216","title":"以前：好开心啊，呵呵 现在：你开心就好，呵呵"},{"images":["https://pic4.zhimg.com/v2-81be18602c9db499ca466a17719c9b4f.jpg"],"type":0,"id":9582545,"ga_prefix":"082215","title":"饿了么收购百度外卖，外卖界「三国鼎立」这回要变 1v1 了"},{"title":"皮皮虾：今天哪也不去，讲讲吃掉我们 N 种方法","ga_prefix":"082214","images":["https://pic1.zhimg.com/v2-bfbbc2dffeabd55b3bb98eee6b19fda4.jpg"],"multipic":true,"type":0,"id":9582854},{"title":"能撑得起简单 T 恤的好身材，胸肌毫无疑问是重点","ga_prefix":"082213","images":["https://pic4.zhimg.com/v2-444a094a8e191bbde66285749e449b93.jpg"],"multipic":true,"type":0,"id":9582205},{"images":["https://pic4.zhimg.com/v2-a83f06634bcce19d200224eb5c09a583.jpg"],"type":0,"id":9576844,"ga_prefix":"082212","title":"大误 · 1 分钟内黑化的世界"},{"images":["https://pic1.zhimg.com/v2-2d1a72c20295be07bb13214ada7d6ba8.jpg"],"type":0,"id":9582532,"ga_prefix":"082211","title":"男作家李枫称遭郭敬明性侵，就算情况属实也很难定罪"},{"images":["https://pic3.zhimg.com/v2-acaf41493eef8259efd6e32eb703063a.jpg"],"type":0,"id":9578502,"ga_prefix":"082210","title":"打牌和打篮球，「手感热得发烫」是真的还是一个错觉？"},{"images":["https://pic4.zhimg.com/v2-d26939abd1d46b123954fb2659cad31f.jpg"],"type":0,"id":9578508,"ga_prefix":"082209","title":"中国人到了国外，是不是很难融入外国人的圈子？"},{"images":["https://pic3.zhimg.com/v2-84df2c61b0c9e8c5df83f82a457b796a.jpg"],"type":0,"id":9579255,"ga_prefix":"082208","title":"之后的 5/50/500 年，人类航天要解决的是这些问题"},{"images":["https://pic3.zhimg.com/v2-9fdb09c71c0943e1ffbf3bd1c82c7352.jpg"],"type":0,"id":9581554,"ga_prefix":"082207","title":"杭州有了「互联网法院」，不过法官律师们早就开始头疼了"},{"images":["https://pic1.zhimg.com/v2-d44989c6f5f6d7cea48e59746719b204.jpg"],"type":0,"id":9581111,"ga_prefix":"082207","title":"9 岁学棋到成为顶尖棋手，其中辛酸只有自己知道"},{"images":["https://pic3.zhimg.com/v2-fba1d32357d7344c36f897e189eadc5e.jpg"],"type":0,"id":8921049,"ga_prefix":"082207","title":"今天，美国人民都在抬头看日全食，而我只能羡慕和嫉妒"},{"images":["https://pic3.zhimg.com/v2-83449dacdd1fa41d12258d76c372a3ba.jpg"],"type":0,"id":9577173,"ga_prefix":"082206","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-ec8026acb9d8a577f10d95722558c7dd.jpg"]
         * type : 0
         * id : 9582980
         * ga_prefix : 082216
         * title : 以前：好开心啊，呵呵 现在：你开心就好，呵呵
         * multipic : true
         */

        private int type;

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }

        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
