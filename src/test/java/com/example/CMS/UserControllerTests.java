//package com.example.CMS;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.example.CMS.Controller.UserController;
//import com.example.CMS.Model.Users;
//import com.example.CMS.Service.UserManagementService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(value = UserController.class)
//@WithMockUser
//class UserControllerTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//	
//    @Mock
//  UserManagementService uservice;
//    
//    
//    @Test
//    public void testSaveUser() throws Exception 
//    {
//    	Users mockUser=new Users();
//    	mockUser.setEmail("a");
//    	mockUser.setFirstname("a");
//    	mockUser.setLastname("a");
//    	mockUser.setPassword("a");
//    	mockUser.setPhone("a");
//    	mockUser.setRole(null);
//    	mockUser.setUsername("a");
//    	
//    	String inputInJson = this.mapToJson(mockUser);
//		
//		String URI = "/user/addUser";
//		
//		Mockito.when(uservice.saveNewUser(Mockito.any(Users.class))).thenReturn(inputInJson);
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post(URI)
//				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//		
//		String outputInJson = response.getContentAsString();
//		
//		assertThat(outputInJson).isEqualTo(inputInJson);
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//    	
//    	
//    }
//    /**
//	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
//	 */
//	private String mapToJson(Object object) throws JsonProcessingException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper.writeValueAsString(object);
//	}
//}
