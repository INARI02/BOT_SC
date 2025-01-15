/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bot_card;

import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class RandomStringGenerator {
    
    private static final String CHARACTERS = "qưertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private static final int LENGTH = 10;
    
    public String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        
        for(int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
