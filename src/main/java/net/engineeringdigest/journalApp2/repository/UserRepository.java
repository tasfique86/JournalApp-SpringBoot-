package net.engineeringdigest.journalApp2.repository;


import net.engineeringdigest.journalApp2.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

    User deleteByUserName(String username);
}
