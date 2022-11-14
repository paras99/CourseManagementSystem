package com.example.CMS.Service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CMS.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserManagementServiceTest {

	@Mock
	private UserRepository userRepository;
	
	private UserManagementService userManagementService;
	
	@BeforeEach
	void setUp() {
		this.userManagementService=new UserManagementService(this.userRepository);
	}
	
	@Test
	void testGetAllUsers() throws Exception {
		userManagementService.getAllUsers(1);
		verify(userRepository).findAll();
	}
	//Before Running this below test case, the user with ROLE::ADMIN should be created as Only ADMIN has rights
	@Test
	void testGetUsersById() throws Exception {
		userManagementService.findUserById(1);
		verify(userRepository).findById(1);
	}

}
