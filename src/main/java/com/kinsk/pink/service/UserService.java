package com.kinsk.pink.service;

import com.kinsk.pink.model.User;
import com.kinsk.pink.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public List<User> findAll(){
        return userRespository.findAll();
    }


    public User findUserById (Long id) {
        if (id!=null){
            Optional<User> userOpt = userRespository.findById(id);
            return userOpt.get();
        }else throw new NotFoundException("User not found");

    }

    public User save(User user) {
        //set a date
        if(user.getStartUser() == null){
            user.setStartUser(new Date());
        }
        return userRespository.save(user);
    }

    public User update(Long id, User user) throws NotFoundException{

        User u = findUserById(id);
        if(u != null) {
            return userRespository.save(conversion(user, u));
        } else {
            throw new NotFoundException("User not found");
        }
    }

        public User conversion (User user, User u){
            //Update name and pass
            u.setName(user.getName());
            u.setPass(user.getPass());
            // Preserve the original creation date
            u.setStartUser(u.getStartUser());
            // Update the last update date to the current time
            u.setLastUpdate(new Date());

            // Returns the same User object with updated properties
            return u;
        }

    //@SneakyThrows // Code that throws a checked exception
    public void deleteById (Long id) throws NotFoundException{
        Optional<User> userOpt = userRespository.findById(id);
        if(userOpt.isPresent()){
            userRespository.deleteById(id);
        } else throw new NotFoundException("User not found");
    }
}
