package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepo.save(user);
        return user;
    }

    public User createAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER", "ADMIN"));
        userRepo.save(user);
        return user;
    }

    public void saveUser(User user){
        userRepo.save(user);
    }


    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User getUserByUserName(String username){
        return userRepo.findByUserName(username);
    }

    public User updateUser(String username, User user) {
        User oldUser = userRepo.findByUserName(username);
        if (oldUser != null) {

            if (user.getUserName() != null && !user.getUserName().isEmpty()) {
                oldUser.setUserName(user.getUserName());
            }

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                oldUser.setPassword(user.getPassword());
            }

            if (user.getJournalEntries() != null && !user.getJournalEntries().isEmpty()) {
                if (oldUser.getJournalEntries() == null) {
                    oldUser.setJournalEntries(new ArrayList<>());
                }
                oldUser.getJournalEntries().addAll(user.getJournalEntries());
            }

            return userRepo.save(oldUser);
        }

        return null;
    }


//    public User deleteUserByUserName(String username){
//        return userRepo.deleteByUserName(username);
//    }

    public void deleteUserByUserName(String username){
        userRepo.deleteByUserName(username);
    }

}
