/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Authenticated user POJO
 * @author Marek
 */
@Entity
@Table(name="REGISTERED_USERS")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private List<String> credentials;

    
    public List<String> getCredentials() {
        return credentials;
    }
    
    public void setCredentials(List<String> credentials) {
        this.credentials = credentials;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String passwordHash = password;
        
        /* Try to make hash of given password */
        try {
            MessageDigest md2 = MessageDigest.getInstance("SHA-256");
            md2.update(password.getBytes());
 
            byte byteData[] = md2.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) {
                    hexString.append('0');
                }
   	     	hexString.append(hex);
            }
            passwordHash = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.password = password;
    }
    
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }
     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }
    
}
