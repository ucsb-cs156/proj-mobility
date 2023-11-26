package edu.ucsb.cs156.mobility.testconfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import edu.ucsb.cs156.mobility.services.CurrentUserService;
import edu.ucsb.cs156.mobility.services.GrantedAuthoritiesService;

@TestConfiguration
public class TestConfig {

    @Bean
    public CurrentUserService currentUserService() {
        return new MockCurrentUserServiceImpl();
    }

    @Bean
    public GrantedAuthoritiesService grantedAuthoritiesService() {
        return new GrantedAuthoritiesService();
    }
}
