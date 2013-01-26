/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;
import cz.muni.fi.pa165.airportmanager.User;
import cz.muni.fi.pa165.airportmanager.UserDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marek
 */
public class AirportAuthenticationManager implements AuthenticationProvider {

 
    
  @Autowired
  private UserDAO userDAO;

  public AirportAuthenticationManager() {
  
  }
  
  public void setUserDAO(UserDAO userDAO) {
      this.userDAO = userDAO;
  }
     
  private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
  
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
      
      if(auth == null){
          throw new BadCredentialsException("Bad Credentials");
      }
      
      String username = auth.getName();
      String password = auth.getCredentials().toString();
      
      User fromDB = userDAO.getUserByUsername(username);
      
      if(fromDB == null && userDAO.findAll().isEmpty()){
          if(username.equals("admin") && password.equals("admin")){
              authorities.add(new SimpleGrantedAuthority("USER"));
              return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
          }
          
          throw new BadCredentialsException("No user with given username found");
      }
      
      // Create new user instance - to hash given password
      User check = new User();
      check.setPassword(password);
      check.setUsername(username);
      
      // Check if passwords are equal
      if (check.getPassword().equals(fromDB.getPassword())) {
            for(int i = 0; i < fromDB.getCredentials().size(); i++){
                authorities.add(new SimpleGrantedAuthority(fromDB.getCredentials().get(i).trim().toUpperCase()));
            }
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), authorities);
      }
      
      throw new BadCredentialsException("Bad Credentials");
  }

    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
