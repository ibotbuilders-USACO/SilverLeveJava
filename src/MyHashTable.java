import java.text.*;
import java.util.*;

public class MyHashTable {
  MyHashTable()
  {
	  
  };
  
  public void TestCase1() throws ParseException
  {
	  
	// Create a hash map
      Hashtable balance = new Hashtable();
      Hashtable StudentRecord = new Hashtable<String, Student>();
      Enumeration names;
      String str;
      double bal;

      balance.put("Zara", new Double(3434.34));
      balance.put("Mahnaz", new Double(123.22));
      balance.put("Ayan", new Double(1378.00));
      balance.put("Daisy", new Double(99.22));
      balance.put("Qadir", new Double(-19.08));
      
      SimpleDateFormat dateformat3 = new SimpleDateFormat("MM/dd/yyyy");
      Date Bdate = dateformat3.parse("11/18/1980");
      StudentRecord.put("Zara", new Student("Jay", "Zara", Bdate));

      // Show all balances in hash table.
      //names = balance.keys();
      names = StudentRecord.keys();
      while(names.hasMoreElements()) {
         str = (String) names.nextElement();
         System.out.println(str + ": " + StudentRecord.get(str).toString());
      }        
      System.out.println();
      
      // Deposit 1,000 into Zara's account
      bal = ((Double)balance.get("Zara")).doubleValue();
      balance.put("Zara", new Double(bal + 1000));
      System.out.println("Zara's new balance: " + balance.get("Zara"));
  }
}
