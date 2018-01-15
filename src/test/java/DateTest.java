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
  public void dateDifference() {
    Date date = new Date("01/10/2018");
    Integer daysDiff = DateUtil.daysBetween(date, DateUtil.getCurrentDate());
    System.out.println("diff:"+ daysDiff);
  }
}
