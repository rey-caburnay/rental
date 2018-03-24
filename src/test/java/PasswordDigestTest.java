import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shinn.util.PasswordDigest;

public class PasswordDigestTest {

  @Test
  public void testDigest() {
    String password = "password";
    String crypted = PasswordDigest.digest(password, PasswordDigest.DEFAULT_SALT);
    System.out.println(crypted);
  }
  
  @Test
  public void testBcrypt() {
    String password = "123456";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);

    System.out.println(hashedPassword);
  }
}
