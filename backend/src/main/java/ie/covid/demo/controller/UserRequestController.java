package ie.covid.demo.controller;

import ie.covid.demo.dto.UserRequestDto;
import ie.covid.demo.dto.UserResponseDto;
import ie.covid.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "https://covid19-tracker-cas-react.herokuapp.com/")
@RestController
@RequestMapping(value = "/api")
public class UserRequestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRequestController.class);

    @Autowired
    UserService userService;

    @PostMapping(value="/getcitydetails")
    public @ResponseBody ResponseEntity registerUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.getAllDetails(userRequestDto.getCountry());

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Access-Control-Allow-Origin","http://localhost:3000");
//        httpHeaders.add("Access-Control-Allow-Credentials","true");
//        httpHeaders.add("Access-Control-Allow-Methods","GET, POST, OPTIONS");
//        httpHeaders.add("Access-Control-Allow-Headers","Origin, Content-Type, Accept");

        return new ResponseEntity(userResponseDto,HttpStatus.OK);
    }
}
