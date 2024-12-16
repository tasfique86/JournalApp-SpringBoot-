package net.engineeringdigest.journalApp2.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserService {

 ///private static final Logger logger= LoggerFactory.getLogger(UserService.class);

 @Autowired
 private UserRepository userRepository;

  public void saveUser(User users) {
   userRepository.save(users);
  }

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

 public boolean saveNewUser(User users) {
 try {
  users.setPassword(passwordEncoder.encode(users.getPassword()));
  users.setRoles(Arrays.asList("USER"));
  userRepository.save(users);
  return true;
 }catch(Exception e) {
  log.error("Error occured for {} :",users.getUserName());
 // log.debug("Error occured for {} :",users.getUserName());
  return false;
 }
 }

 public boolean saveAdmin(User users) {
  try {
   users.setPassword(passwordEncoder.encode(users.getPassword()));
   users.setRoles(Arrays.asList("USER","ADMIN"));
   userRepository.save(users);
   return true;
  }
  catch (Exception e) {
  // logger.info("hahahahahah");
   System.out.println(e);
   return false;
  }
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
