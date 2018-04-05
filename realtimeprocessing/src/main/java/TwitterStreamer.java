import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamer {

    public static void main(String[] args) {
        final Configuration configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("CONSUMER_KEY") // paste your consumer key
                .setOAuthConsumerSecret("CONSUMER_SECRET") //paste your CONSUMER_SECRET
                .setOAuthAccessToken("OAUTH_ACCESS_TOKEN") //paste your OAUTH_ACCESS_TOKEN
                .setOAuthAccessTokenSecret("OAUTH_ACCESS_TOKEN_SECRET") // paste your OAUTH_ACCESS_TOKEN
                .build();

        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();

    }
}
