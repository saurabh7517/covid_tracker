package ie.covid.demo.dto;

public class UserRequestDto {
    private String country;

    public UserRequestDto() {}

    public UserRequestDto(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
