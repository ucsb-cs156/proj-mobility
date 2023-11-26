package edu.ucsb.cs156.mobility.controllers;

import edu.ucsb.cs156.mobility.ControllerTestCase;
import edu.ucsb.cs156.mobility.controllers.DriversController;
import edu.ucsb.cs156.mobility.entities.User;
import edu.ucsb.cs156.mobility.repositories.UserRepository;
import edu.ucsb.cs156.mobility.testconfig.TestConfig;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@WebMvcTest(controllers = DriversController.class)
@Import(TestConfig.class)
public class DriversControllerTests extends ControllerTestCase {

  @MockBean
  UserRepository userRepository;

  @Test
  public void users__logged_out() throws Exception {
    mockMvc.perform(get("/api/drivers/all"))
        .andExpect(status().is(403));
  }

   @WithMockUser(roles = { "ADMIN" })
        @Test
        public void test1() throws Exception {

                

                User user1 = User.builder()
                                .email("a")
                                .googleSub("a")
                                .pictureUrl("a")
                                .fullName("a")
                                .givenName("a")
                                .familyName("a")
                                .emailVerified(true)
                                .locale("a")
                                .hostedDomain("a")
                                .cellPhone("a")
                                .admin(false)
                                .driver(true)
                                .rider(false)
                                .build();
                ArrayList<User> exp = new ArrayList<>();
                exp.addAll(Arrays.asList(user1));

                when(userRepository.findByDriver(true)).thenReturn(exp);  

            
                MvcResult response = mockMvc.perform(get("/api/drivers/all"))
                                .andExpect(status().isOk()).andReturn();

             

                verify(userRepository, times(1)).findByDriver(true);
                String expectedJson = mapper.writeValueAsString(exp);
                String responseString = response.getResponse().getContentAsString();
                assertEquals(expectedJson, responseString);
        }
}