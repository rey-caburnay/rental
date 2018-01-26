import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shinn.configuration.test.TestContext;
import com.shinn.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class DateTest {

  @Test
  public void itShouldReturnNegative() {
    Date date = new Date("01/25/2018");
    Integer daysDiff = DateUtil.daysDiff(date, DateUtil.getCurrentDate());
    System.out.println("diff:" + daysDiff);
    assertTrue(daysDiff > 0);
  }
  
  @Test
  public void itShouldReturnPositive() {
    Date date = new Date("01/15/2018");
    Integer daysDiff = DateUtil.daysDiff(date, DateUtil.getCurrentDate());
//    System.out.println("diff with abs:" + Math.abs(daysDiff));
//    if(daysDiff >= -3 && daysDiff <= 3 ) {
//      System.out.println("diff with abs:" + daysDiff);
//    }
    assertTrue((daysDiff >= -3 && daysDiff <= 3 ));
    
  }

//  @Test
//  public void givenTwoDatesInJava8_whenDifferentiating_thenWeGetSix() {
//    LocalDate now = LocalDate.now();
//    LocalDate sixDaysBehind = now.minusDays(6);
//    Date dueDate = new Date("01/20/2018");
//    Duration duration = Duration.between(now, dueDate);
//    Duration.
//    long diff = Math.abs(duration.toDays());
//    assertEquals(diff, 6);
//  }
}
