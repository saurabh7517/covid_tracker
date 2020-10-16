package ie.covid.demo.dto;

import ie.covid.demo.model.News;

import java.util.List;

public class UserResponseDto {
    private Integer cases;
    private Integer deaths;
    private Integer recovered;
    private List<String> twitterMessages;
    private List<News> currentNews;

    public UserResponseDto(Integer cases, Integer deaths, Integer recovered, List<String> twitterMessages, List<News> currentNews) {
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.twitterMessages = twitterMessages;
        this.currentNews = currentNews;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public List<String> getTwitterMessages() {
        return twitterMessages;
    }

    public void setTwitterMessages(List<String> twitterMessages) {
        this.twitterMessages = twitterMessages;
    }

    public List<News> getCurrentNews() {
        return currentNews;
    }

    public void setCurrentNews(List<News> currentNews) {
        this.currentNews = currentNews;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }
}
