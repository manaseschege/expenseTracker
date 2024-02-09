package com.Chei.ExpenseTracker.Service;

import com.Chei.ExpenseTracker.Entity.Users;
import com.Chei.ExpenseTracker.Entity.UserModel;
import com.Chei.ExpenseTracker.Repository.UserRepository;
import com.Chei.ExpenseTracker.exeptions.ItemAlreadyExistsExeption;
import com.Chei.ExpenseTracker.exeptions.ResourceNotFoundExeption;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;
@Autowired
private PasswordEncoder bcryptEncoder;



    @Override
    public Users createUser(UserModel user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistsExeption(("email already Exists :" + user.getEmail()));
        }
Users newUser=new Users();
        BeanUtils.copyProperties(user,newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public Users readUser() {
Long userId=getLoggedInUser().getId();
return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundExeption("User not found for the id : "+userId));
    }

    @Override
    public Users updateUser( UserModel user) {

        Users existingUser = readUser();
        existingUser.setName(existingUser.getName()!=null?user.getName():existingUser.getName());
        existingUser.setEmail(existingUser.getEmail()!=null?user.getEmail():existingUser.getEmail());
        existingUser.setAge(existingUser.getAge()!=null?user.getAge():existingUser.getAge() );
        existingUser.setPassword(existingUser.getPassword()!=null?bcryptEncoder.encode(user.getPassword()):existingUser.getPassword());
        return userRepository.save(existingUser);

//        Optional<User> user1=userRepository.findById(id);
//        if(user1.isPresent()) {
//            user1.get().setName(user1.get().getName()!=null?user1.get().getName():user.getName());
//            user1.get().setEmail(user1.get().getEmail()!=null?user1.get().getEmail():user.getEmail());
//            user1.get().setAge(user1.get().getAge()!=null?user1.get().getAge() : user.getAge());
//            User newUser=new User();
//            BeanUtils.copyProperties(user,newUser);
//            return userRepository.save(newUser);
//        }
//        throw new ResourceNotFoundExeption("User not found for the id : \"+id");
    }

    @Override
    public void deleteUser() {
        Users user = readUser();
        userRepository.deleteById(user.getId());
    }

    @Override
    public Users getLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email =authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found fot the email :"+email));

    }

}