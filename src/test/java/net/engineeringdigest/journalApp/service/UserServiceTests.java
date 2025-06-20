package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private UserService userService;


    @Test
    public void testAdd(){
        assertEquals(4, 2+1);
        System.out.println("Succeed");
    }

    @ParameterizedTest
    @ArgumentsSource(USerArgumentProviders.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.createUser(user));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "1, 2, 3",
            "5, 8, 13",
            "4, 5, 1"
    })
    public void test(int a, int b, int expected){
        assertEquals(a + b, expected);
    }


    @ParameterizedTest
    @CsvSource({
            "ram",
            "rajat",
            "rohan",
            "naman",
            "@k@sh",
            "amit"
    })
    public void testUser(String name){
        assertNotNull(userRepo.findByUserName(name), "failed for "+name);
        assertTrue(userRepo.findByUserName(name).getJournalEntries().isEmpty(), "failed for "+name);
    }
}
