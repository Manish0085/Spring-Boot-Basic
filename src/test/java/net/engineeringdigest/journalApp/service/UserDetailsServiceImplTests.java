package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private CustomUserDetailService userService;


    @Mock
    private UserRepo userRepo;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserName(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Manish").password("kijchgdnsmsgH").roles(new ArrayList<>()).build());
        UserDetails userDetails = userService.loadUserByUsername("RAM");
        System.out.println(userDetails.toString());
        assertNotNull(userDetails);
    }
}
