package com.collageproject.learntogether;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RedditPostData implements Serializable {
    @SerializedName("data")
    private RedditPost post;

    public RedditPost getPost() {
        return post;
    }

    public void setPost(RedditPost post) {
        this.post = post;
    }
}

class RedditPost {
    @SerializedName("title")
    private String title;

    @SerializedName("url")
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
}
