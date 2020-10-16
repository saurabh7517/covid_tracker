package ie.covid.demo;

import ie.covid.demo.dto.UserRequestDto;
import ie.covid.demo.dto.UserResponseDto;
import ie.covid.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CovidApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserService userService;

    @Test
    public void testUserRequestController(){
        UserRequestDto userRequestDto = new UserRequestDto("spain");
        UserResponseDto userResponseDto = userService.getAllDetails(userRequestDto.getCountry());
        assertTrue(userResponseDto.getTwitterMessages().size()>0);


    }




}
