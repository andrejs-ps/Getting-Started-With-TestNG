package app;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Collection;

public class UserManager {

    private Collection<String> userStorage;

    public UserManager(){
        userStorage = new ArrayList<>();
    }


    public boolean addUser(String userEmail) throws DuplicateUserException {
        if(!EmailValidator.getInstance().isValid(userEmail)){
            throw new IllegalArgumentException("Not a valid email");
        }
        for(String s : userStorage){
            if(s.equals(userEmail)){
                throw new DuplicateUserException("User already exists");
            }
        }
        return userStorage.add(userEmail);
    }

    public String getUser(String userEmail){
        for(String s : userStorage){
            if(s.equals(userEmail)){
                return s;
            }
        }
        return null;
    }

    public boolean deleteUser(final String userEmail){
        return userStorage.removeIf(e -> e.equals(userEmail));
    }

    public Collection<String> getAllUsers(){
        return userStorage;
    }

}
