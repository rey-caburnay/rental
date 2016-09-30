import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

public class BasicTest {
    public static void main(String args[]) {
        
        Date d = Calendar.getInstance().getTime();
        Date e = Calendar.getInstance().getTime();
        String t = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-09-2016 10:20:56";
        
        String smsmessage = RentStatus.BEFORE_DUE_MESSAGE;
//        if (smsmessage.indexOf("{amount}") > 0 ) {
//            System.out.println("found using {}" + smsmessage.indexOf("{amount}"));
//        }
//        if (smsmessage.indexOf("\\{amount\\}") > 0 ) {
//            System.out.println("found using \\{\\}" + smsmessage.indexOf("\\{amount\\}"));
//        }
        smsmessage = smsmessage.replaceAll("\\{amount\\}", 500d +"");
        smsmessage = smsmessage.replace("{duedate}", DateUtil.getNameOfMonth(d.getMonth()));
        
        System.out.println(smsmessage);
        System.out.println("month: " + DateUtil.getNameOfMonth(d.getMonth()));
       
        
        try {
            Date date = sdf.parse(dateInString);
            System.out.println(DateUtil.addDays(d, 30));
            System.out.println("number of days:" + DateUtil.daysBetween(d, e));
        } catch (ParseException pe) {
            // TODO Auto-generated catch block
            pe.printStackTrace();
        }
    }
}
