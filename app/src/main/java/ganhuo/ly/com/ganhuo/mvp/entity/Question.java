package ganhuo.ly.com.ganhuo.mvp.entity;


import ganhuo.ly.com.ganhuo.common.Constant;

public class Question {
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isValidZhihuQuestion() {
        return url != null && url.startsWith(Constant.Strings.ZHIHU_QUESTION_LINK_PREFIX);
    }
}

