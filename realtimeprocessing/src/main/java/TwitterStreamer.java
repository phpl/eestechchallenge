import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamer {

    public static void main(String[] args) {
        final Configuration configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("PNNtwKHpzQoHX9IxAktBGZiWT") // paste your consumer key
                .setOAuthConsumerSecret("wNA73UotTsC6izNgxeydjxYJnJYrCdFMx8D7Ntu4ZNH0BxlSTU") //paste your CONSUMER_SECRET
                .setOAuthAccessToken("981942146923220992-q0mJxqYPbaA6dXduUJ7lr6vxw7JCCGD") //paste your OAUTH_ACCESS_TOKEN
                .setOAuthAccessTokenSecret("7O8hSJSXy4JpgBaM1oxbMrbQ2YHwvOKbtQzSKzfPb4pcT") // paste your OAUTH_ACCESS_TOKEN
                .build();

        StatusListener listener = new StatusListener() {

            public void onStatus(Status status) {

                System.out.println(status.getText());
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onScrubGeo(long l, long l1) {

            }

            public void onStallWarning(StallWarning stallWarning) {

            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery().track("facebook", "instagram", "twitter"));


    }
}
