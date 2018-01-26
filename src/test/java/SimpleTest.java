import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.util.StringUtils;

import com.shinn.service.model.ElectricBill;
import com.shinn.util.RentStatus;

public class SimpleTest {

  @Test
  public void testDoubleNegative() {
    String negative = "-500";
    System.out.println(Double.parseDouble(negative));
  }
  
  @Test
  public void isNullOrEmpty() {
    ElectricBill bill = new ElectricBill();
    assertTrue(StringUtils.isEmpty(bill));
  }
  
  @Test
  public void isEquals() {
   Integer day = 10;
   assertTrue(day.equals(RentStatus.DAYS_TO_GENERATE_BILL));
  }
}
