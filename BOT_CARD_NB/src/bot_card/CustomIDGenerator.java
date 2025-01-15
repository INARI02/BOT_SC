
package bot_card;

import java.time.LocalDate;
import java.util.Random;

public class CustomIDGenerator {

    public static String generateID(int age) {
        
        int currentYear = LocalDate.now().getYear();
        int birthYear = currentYear - age;
        String yearSuffix = String.format("%02d", birthYear%100);
        
        // Random 0 - 9999
        Random random = new Random();
        int randomDigit = random.nextInt(10000);
        String randomPart = String.format("%04d", randomDigit);
        
        // Ghep
        return String.format("ID-%s%s", yearSuffix,randomPart);

    }
    
}
