package ie.covid.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class TwitterService {
    public TwitterFactory tf;
    public Twitter twitter;

    @Value("${oauth.consumerKey}")
    private String consumerKey;

    @Value("${oauth.consumerSecret}")
    private String consumerSecret;

    @Value("${oauth.accessToken}")
    private String accessToken;

    @Value("${oauth.accessTokenSecret}")
    private String accessTokenSecret;



    @PostConstruct
    public void init(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public Future<List> searchTweetsAsync(){
        CompletableFuture<List> futureTweet = new CompletableFuture<>();
        new Thread(() -> {

            try{
                Query query = new Query("source:#covid-19");
                QueryResult result = twitter.search(query);
                List<String>  messages = result.getTweets().stream()
                        .map(item -> item.getText())
                        .collect(Collectors.toList());
                futureTweet.complete(messages);
            }catch (Exception e){
                futureTweet.completeExceptionally(e);
            }
        }).start();
        return futureTweet;
    }

}
