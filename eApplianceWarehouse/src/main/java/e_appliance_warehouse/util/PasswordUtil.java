package e_appliance_warehouse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    /**
     * 
     * @param plainPassword
     * @return
     */
    public static String hashPassword(String plainPassword) {
        String hashedPassword = null;
        try 
        {
          MessageDigest md = MessageDigest.getInstance("MD5");
          md.update(plainPassword.getBytes());
          byte[] bytes = md.digest();
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }
          hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        } 
        
        return hashedPassword;
    }
    
    /**
     *     
     * @param enteredPassword
     * @param savedPassword
     * @return
     */
    public static Boolean validatePassword(String enteredPassword, String savedPassword) {
    	boolean resp = false;
    	if(hashPassword(enteredPassword).equals(savedPassword)) resp = true;  
    	
    	return resp;
    }

}
