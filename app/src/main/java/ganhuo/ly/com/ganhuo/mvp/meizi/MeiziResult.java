package ganhuo.ly.com.ganhuo.mvp.meizi;

import java.util.List;

public class MeiziResult {

    /**
     * count : 65
     * next : http://127.0.0.1:8000/getlistpic?limit=5&mid=135082&offset=25
     * previous : http://127.0.0.1:8000/getlistpic?limit=5&mid=135082&offset=15
     * results : [{"id":73,"mid":"135082","title":"尤果女神程瑜西性感火辣充满御姐气质","picname":"18b21","page_url":"http://www.mzitu.com/135082/21","img_url":"http://i.meizitu.net/2018/05/18b21.jpg"},{"id":74,"mid":"135082","title":"尤果女神程瑜西性感火辣充满御姐气质","picname":"18b22","page_url":"http://www.mzitu.com/135082/22","img_url":"http://i.meizitu.net/2018/05/18b22.jpg"},{"id":75,"mid":"135082","title":"尤果女神程瑜西性感火辣充满御姐气质","picname":"18b23","page_url":"http://www.mzitu.com/135082/23","img_url":"http://i.meizitu.net/2018/05/18b23.jpg"},{"id":76,"mid":"135082","title":"尤果女神程瑜西性感火辣充满御姐气质","picname":"18b24","page_url":"http://www.mzitu.com/135082/24","img_url":"http://i.meizitu.net/2018/05/18b24.jpg"},{"id":77,"mid":"135082","title":"尤果女神程瑜西性感火辣充满御姐气质","picname":"18b25","page_url":"http://www.mzitu.com/135082/25","img_url":"http://i.meizitu.net/2018/05/18b25.jpg"}]
     */

    private int count;
    private String next;
    private String previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 73
         * mid : 135082
         * title : 尤果女神程瑜西性感火辣充满御姐气质
         * picname : 18b21
         * page_url : http://www.mzitu.com/135082/21
         * img_url : http://i.meizitu.net/2018/05/18b21.jpg
         */

        private int id;
        private String mid;
        private String title;
        private String picname;
        private String page_url;
        private String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicname() {
            return picname;
        }

        public void setPicname(String picname) {
            this.picname = picname;
        }

        public String getPage_url() {
            return page_url;
        }

        public void setPage_url(String page_url) {
            this.page_url = page_url;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
