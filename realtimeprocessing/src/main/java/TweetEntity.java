import java.time.LocalDate;

public class TweetEntity {
    private Integer id;
    private String keyword;
    private LocalDate tweetDate;
    private String language;
    private String country;
    private Integer followersLength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public LocalDate getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(LocalDate tweetDate) {
        this.tweetDate = tweetDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFollowersLength() {
        return followersLength;
    }

    public void setFollowersLength(Integer followersLength) {
        this.followersLength = followersLength;
    }
}
