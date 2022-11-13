//package com.example.CMS;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import com.example.CMS.Model.Users;
//import com.example.CMS.Repository.UserRepository;
//import com.example.CMS.Service.UserManagementService;
//
//class UserControllerServiceTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserManagementService userService;
//
//    private Users user;
//	   // JUnit test for saveUsers method
//    @DisplayName("JUnit test for saveUsers method")
//    @Test
//    public void givenUsersObject_whenSaveUsers_thenReturnUsersObject(){
//        // given - precondition or setup
//        given(userRepository.findByEmail(user.getEmail()))
//                .willReturn(Optional.empty());
//
//        given(userRepository.save(user)).willReturn(user);
//
//        System.out.println(userRepository);
//        System.out.println(userService);
//
//        // when -  action or the behaviour that we are going test
//        Users savedUsers = userService.saveNewUser(user);
//
//        System.out.println(savedUsers);
//        // then - verify the output
//        assertThat(savedUsers).isNotNull();
//    }
//}
