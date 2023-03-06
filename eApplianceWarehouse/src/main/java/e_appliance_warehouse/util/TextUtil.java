package e_appliance_warehouse.util;

import java.util.Base64;    

public class TextUtil {

	/**
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encryptText(String plainText) {
        String b64encoded = Base64.getEncoder().encodeToString(plainText.getBytes());
        String reverse = new StringBuffer(b64encoded).reverse().toString();
        StringBuilder encryptedText = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
        	encryptedText.append((char)(reverse.charAt(i) + OFFSET));
        }
        
        return encryptedText.toString();
    }

    /**
     * 
     * @param encryptedText
     * @return
     */
	public static String decryptText(String encryptedText) {
 	   StringBuilder sb = new StringBuilder();
 	   final int OFFSET = 4;
 	   for (int i = 0; i < encryptedText.length(); i++) {
 		   sb.append((char)(encryptedText.charAt(i) - OFFSET));
 	   }
 	   String decryptedText = new StringBuffer(sb.toString()).reverse().toString();
 	   
 	   return new String(Base64.getDecoder().decode(decryptedText));
    }

}
