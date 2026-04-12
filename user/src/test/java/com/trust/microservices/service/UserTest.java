//package com.trust.service;
//
//import com.trust.model.User;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Timestamp;
//
//@SpringBootTest
//public class UserTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void createUserTest(){
//        User user = User.builder()
//                .userName("saiteja")
//                .email("saiteja@gmail.com")
//                .createdOn(new Timestamp(System.currentTimeMillis()))
//                .firstName("sai teja")
//                .lastName("jambarapu")
//                .modifiedOn(new Timestamp(System.currentTimeMillis()))
//                .roleId(2)
//                .phoneNumber("6281142931")
//                .password("saiteja2714")
//                .isActive(true)
//                .build();
//        User u = userService.createUser(user);
//        System.out.println(u.toString());
//
//    }
//
//
//}
