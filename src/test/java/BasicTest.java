import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.assertj.core.util.Lists;
import org.slf4j.LoggerFactory;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.factory.NamedParameterPreparedStatement;
import com.shinn.service.model.Transaction;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

public class BasicTest {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BasicTest.class);
    
    public static void main(String args[]) {
        
        BasicTest test = new BasicTest();
//        test.testNumberFormat();
//        test.testDecimalFormat();
//        test.continueExample();
        test.dateTest();
//        test.testMath();
    }
    
    public void testMath() {
      Double overdue = -6000d;
      Double deposit = 0d;
      Double total = 0d;
      
      
      total = overdue + 3000d;
      System.out.println(total);
    }
    
    public void continueExample() {
//      List<String> strings = Lists.newArrayList();
//      strings.add("1");
//      strings.add("2");
//      strings.add("3");
//      strings.add("4");
//      strings.add("5");
//      strings.add("6");
//      strings.add("1");
//      strings.add("2");
//      strings.add("3");
//      strings.add("4");
//      strings.add("5");
//      strings.add("6");
//      
//      
//      for (String s : strings) {
//        if (s.equals("1")) {
//          continue;
//        }
//        System.out.println(s);
//      }
      
    }
    
    public void testNumberFormat() {
      
      Locale swedish = new Locale("ph", "PH");
      NumberFormat formatter = NumberFormat.getCurrencyInstance(swedish);
      double money = 50000.10d;
      System.out.println(formatter.format(money));
    }
    
    public void testDecimalFormat() {
      DecimalFormat myFormatter = new DecimalFormat("###,###.###");
      double money = 50000.00d;
      String output = myFormatter.format(money);
      System.out.println(output);
    }
    public void getMethodTest() {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        
        String sql = " UPDATE `realmaster`.`tx_rental` SET `id` = :id, `aptId` = :aptId, " +
                        "`roomId` = :roomId, `dueDate` = :dueDate, `txDate` = :txDate," +
                        "`startDate` = :startDate, `endDate` = :endDate, `deposit` = :deposit," +
                        "`renterId` = :renterId, `balance` = :balance, `txType` = :txType, " +
                        "`provider` = :provider, `amount` = :amount, `status` = :status `userId` = :userId" +
                        "`updatedDate` = :updateDate,  `updtCnt` = :updtCnt" + 
                        " WHERE `id`= @`id` AND `aptId` = @`aptId` AND `roomId` = @`roomId`;";
        
        Transaction tx = new Transaction();
        tx.setId(1);
        tx.setAptId(1);
        tx.setRoomId(1);
        tx.setDueDate(new Date());
//        T type = tx.getClass();
        try {
            
            //String parsedSql = NamedParameterParser.parse(sql, indexMap);
            
            
            for (PropertyDescriptor pd : Introspector.getBeanInfo(tx.getClass()).getPropertyDescriptors()) {
//                logger.info("method name: " +pd.getReadMethod().invoke(tx));
//                System.out.println("method name: " +pd.getReadMethod().invoke(tx));
                if (pd.getReadMethod() != null && !"class".equals(pd.getName()))
                    if (indexMap.containsKey(pd.getName())) {
                        logger.info("method name in indexMap: " + pd.getName() + "value : "+pd.getReadMethod().invoke(tx));
                        System.out.println("method name in indexMap: " + pd.getName() + "value : "+pd.getReadMethod().invoke(tx));
                    }
                    
              }
        } catch (Exception e) {
            logger.info(e.getCause() +" " + e);

        }
    }
    
    public void dateTest() {
        Date d = Calendar.getInstance().getTime();
        Date e = Calendar.getInstance().getTime();
        String t = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-09-2016 10:20:56";
        
        String smsmessage = "Please pay your rent for the month of {month} amounting:{amount} to avoid neccessary actions. from-Caburany Apartmentz";
//        if (smsmessage.indexOf("{amount}") > 0 ) {
//            System.out.println("found using {}" + smsmessage.indexOf("{amount}"));
//        }
//        if (smsmessage.indexOf("\\{amount\\}") > 0 ) {
//            System.out.println("found using \\{\\}" + smsmessage.indexOf("\\{amount\\}"));
//        }
        smsmessage = smsmessage.replaceAll("\\{amount\\}", 500d +"");
        smsmessage = smsmessage.replace("{month}", DateUtil.getNameOfMonth(d.getMonth()));
        
        System.out.println(smsmessage);
        System.out.println("month: " + DateUtil.getNameOfMonth(d.getMonth()));
        UUID idOne = UUID.randomUUID();
        System.out.println(String.valueOf(idOne).substring(0,32));
        
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
