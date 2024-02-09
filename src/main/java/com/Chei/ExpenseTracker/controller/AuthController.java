package com.Chei.ExpenseTracker.controller;

import com.Chei.ExpenseTracker.Entity.AuthModel;
import com.Chei.ExpenseTracker.Entity.JwtResponse;
import com.Chei.ExpenseTracker.Entity.Users;
import com.Chei.ExpenseTracker.Entity.UserModel;
import com.Chei.ExpenseTracker.Service.UserService;
import com.Chei.ExpenseTracker.util.JwtTokenUtil;
import com.Chei.ExpenseTracker.security.CustomUserDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private CustomUserDetailService userDetailService;
    private JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {

    authenticate(authModel.getEmail(),authModel.getPassword());
final UserDetails userDetails=userDetailService.loadUserByUsername(authModel.getEmail());

    final String token=jwtTokenUtil.generateToken(userDetails);

return  new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
}
private void authenticate(String email,String password)throws Exception {
  try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
  }catch(DisabledException e){
throw  new Exception("User disabled");
  }catch (BadCredentialsException e){
      throw  new Exception("Bad credentials");
  }
}
    @PostMapping("/register")
    public ResponseEntity<Users> save(@Valid @RequestBody UserModel user){
        return new ResponseEntity<Users>(userService.createUser(user), HttpStatus.CREATED);
    }

}
