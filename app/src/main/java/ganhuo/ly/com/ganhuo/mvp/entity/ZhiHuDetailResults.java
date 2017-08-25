package ganhuo.ly.com.ganhuo.mvp.entity;

import java.util.List;

/**
 * Created by liuyu1 on 2017/8/24.
 */

public class ZhiHuDetailResults {

    /**
     * body :
     * image_source : 视觉中国
     * title : 百度与今日头条传绯闻，是为了争抢这项「吸金」业务
     * image : https://pic3.zhimg.com/v2-429b5df7d42c58802b3e031fe597623e.jpg
     * share_url : http://daily.zhihu.com/story/9584440
     * js : []
     * ga_prefix : 082315
     * images : ["https://pic1.zhimg.com/v2-09c49eb6c28dad97ddbf053bc4f1df10.jpg"]
     * type : 0
     * id : 9584440
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;

    @Override
    public String toString() {
        return "ZhiHuDetailResults{" +
                "image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }

    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
