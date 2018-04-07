import akka.actor.AbstractActor;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import scala.Tuple2;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class tweetActor extends AbstractActor {

    Map<String, Integer> tagsToCount = new HashMap<>();
    private int oldMinute;
    private int currentMinute;
    public tweetActor() {
        tagsToCount.put("beer", 0);
        tagsToCount.put("trump", 0);
        tagsToCount.put("facebook", 0);
        tagsToCount.put("isis", 0);
        tagsToCount.put("AI", 0);
        tagsToCount.put("EA", 0);
        tagsToCount.put("Poland", 0);
        tagsToCount.put("holidays", 0);
        tagsToCount.put("christmas", 0);
        tagsToCount.put("hawking", 0);
        oldMinute = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).getMinute();
        currentMinute = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).getMinute();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TweetDateTuple.class, tweet -> {
                    if (LocalDateTime.ofInstant(tweet.getCreatedDate().toInstant(), ZoneOffset.UTC).getMinute() - currentMinute <= 0) {
                        for (Map.Entry<String, Integer> entry : tagsToCount.entrySet()) {
                            if (tweet.getTweet().toLowerCase().contains(entry.getKey().toLowerCase())) {
                                tagsToCount.put(entry.getKey(), tagsToCount.get(entry.getKey()) + 1);
                            }
                        }
                    } else {
                        for (Map.Entry<String, Integer> entry : tagsToCount.entrySet()) {
                            System.out.println(entry.getKey()+" : "+entry.getValue());
                        }
                        tagsToCount.put("beer", 0);
                        tagsToCount.put("trump", 0);
                        tagsToCount.put("facebook", 0);
                        tagsToCount.put("isis", 0);
                        tagsToCount.put("AI", 0);
                        tagsToCount.put("EA", 0);
                        tagsToCount.put("Poland", 0);
                        tagsToCount.put("holidays", 0);
                        tagsToCount.put("christmas", 0);
                        tagsToCount.put("hawking", 0);
                    }

                    if (currentMinute - oldMinute <= 0) {
                        oldMinute = currentMinute;
                        currentMinute = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).getMinute();
                    }



                }).build();
    }
}
