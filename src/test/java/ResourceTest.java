import org.junit.Test;

public class ResourceTest {

  @Test
  public void getResourcePath() {
    System.out.println(ResourceTest.class.getResource("").getFile());
  }
}
