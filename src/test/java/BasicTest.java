import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import com.shinn.util.DateUtil;
import com.shinn.util.StringUtil;

public class BasicTest {
    public static void main(String args[]) {
        
        Date d = Calendar.getInstance().getTime();
        String t = "";
        
        try {
            System.out.println(DateUtil.addDays(d, 30, null));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
