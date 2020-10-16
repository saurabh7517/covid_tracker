package ie.covid.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Corona {

    private String Country;

    private String Date;
    private Integer Cases;
    private String Status;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;

    public Corona(){}

    public Corona(String country, String date, Integer cases, String status, Integer confirmed, Integer deaths, Integer recovered) {
        Country = country;
        Date = date;
        Cases = cases;
        Status = status;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCountry() {
        return Country;
    }
    @JsonSetter("Country")
    public void setCountry(String country) {
        Country = country;
    }

    public String getDate() {
        return Date;
    }
    @JsonSetter("Date")
    public void setDate(String date) {
        Date = date;
    }

    public Integer getCases() {
        return Cases;
    }

    public void setCases(Integer cases) {
        Cases = cases;
    }

    public String getStatus() {
        return Status;
    }
    @JsonSetter("Status")
    public void setStatus(String status) {
        Status = status;
    }

    public Integer getConfirmed() {
        return confirmed;
    }
    @JsonSetter("Confirmed")
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeaths() {
        return deaths;
    }
    @JsonSetter("Deaths")
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }
    @JsonSetter("Recovered")
    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }
}
