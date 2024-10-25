package net.engineeringdigest.journalApp2.service;

import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {

 @Autowired
 private UserRepository userRepository;

  public void saveUser(User users) {
   userRepository.save(users);
  }

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

 public void saveNewUser(User users) {
  users.setPassword(passwordEncoder.encode(users.getPassword()));
  users.setRoles(Arrays.asList("USER"));
  userRepository.save(users);
 }
 public void saveAdmin(User users) {
  users.setPassword(passwordEncoder.encode(users.getPassword()));
  users.setRoles(Arrays.asList("USER","ADMIN"));
  userRepository.save(users);
 }

  public List<User> findAll() {
   return userRepository.findAll();
  }

  public User findById(ObjectId id) {
   return userRepository.findById(id).orElse(null);

  }

  public void delete(ObjectId id) {
   userRepository.deleteById(id);
  }


  public User findByUsername(String userName) {
   return userRepository.findByUserName(userName);
  }
//
// public void updateJournalById(Users users) {
//  userRepository.save(users);
// }
}
