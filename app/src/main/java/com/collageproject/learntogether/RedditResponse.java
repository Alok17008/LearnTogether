package com.collageproject.learntogether;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RedditResponse {
    @SerializedName("data")
    private RedditData data;

    public RedditData getData() {
        return data;
    }

    public void setData(RedditData data) {
        this.data = data;
    }
}

class RedditData {
    @SerializedName("children")
    private List<RedditPostData> children;

    public List<RedditPostData> getChildren() {
        return children;
    }

    public void setChildren(List<RedditPostData> children) {
        this.children = children;
    }
}
