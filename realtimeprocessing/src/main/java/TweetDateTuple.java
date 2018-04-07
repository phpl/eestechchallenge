import java.util.Date;

public class TweetDateTuple {
    private String tweet;
    private Date createdDate;

    public TweetDateTuple(String tweet, Date createdDate) {
        this.tweet = tweet;
        this.createdDate = createdDate;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

