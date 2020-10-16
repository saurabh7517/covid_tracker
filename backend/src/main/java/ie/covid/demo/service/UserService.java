package ie.covid.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.covid.demo.dto.UserResponseDto;
import ie.covid.demo.model.Corona;
import ie.covid.demo.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    RestTemplate restTemplate;
    ObjectMapper objectMapper;

    @Value("${twitter_api}")
    private String twitterApiUrl;

    @Value("${covid_api}")
    private String covidApiUrl;

    @Value("${news_api}")
    private String newApiUrl;

    @Autowired
    TwitterService twitterService;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
    }

    public UserResponseDto getAllDetails(String country) {
        UserResponseDto userResponseDto = null;
        Future<Corona> futureCorona= getCOVIDDataAsync(country);
        Future<News[]> futureNews = getNewsDataAsync();
        Future<List> futureTweets = twitterService.searchTweetsAsync();

        try{
            Corona corona = futureCorona.get(5, TimeUnit.SECONDS);
            News[] news = futureNews.get(5, TimeUnit.SECONDS);
            List<String> messages = futureTweets.get(5,TimeUnit.SECONDS);
            List<News> newsList = Arrays.asList(news);
            userResponseDto = new UserResponseDto(corona.getConfirmed(),corona.getDeaths(),corona.getRecovered(),messages,newsList);
        }catch (InterruptedException e){
            logger.error(e.getMessage());
        }catch (ExecutionException e){
            logger.error(e.getMessage());
        }catch (TimeoutException e){
            logger.error(e.getMessage());
        }
        return userResponseDto;
    }

    public Future<Corona> getCOVIDDataAsync(String country){
        CompletableFuture<Corona> futureData = new CompletableFuture<>();
        new Thread(() -> {
            try{
                ResponseEntity<Corona[]> response = restTemplate.getForEntity(covidApiUrl + country + "/status/confirmed", Corona[].class);
                Corona[] coronas = response.getBody();
                futureData.complete(coronas[coronas.length-1]);
            }catch (Exception e){
                futureData.completeExceptionally(e);
                logger.error("Error in fetching Corona Data");
            }
        }).start();
        return futureData;
    }

    public Future<News[]> getNewsDataAsync(){
        CompletableFuture<News[]> futureNews = new CompletableFuture<>();
        new Thread( () -> {
            JsonNode root = null;
            JsonNode newsRoot;


            ResponseEntity<String> response = restTemplate.getForEntity(newApiUrl, String.class);
            try {
                root = objectMapper.readTree(response.getBody());
                newsRoot = root.path("news");

                News[] news = objectMapper.convertValue(newsRoot, News[].class);
                futureNews.complete(news);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error("Parsing error in news data");

            }catch (Exception e){
                logger.error("Error in Completable Future");
            }


        }).start();
        return futureNews;
    }

    public String getYesterday(){
        LocalDateTime today =  LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss'Z'");
        Date  finalDate = Date.from( yesterday.atZone( ZoneId.systemDefault()).toInstant());
        return f.format(finalDate);
    }

}
