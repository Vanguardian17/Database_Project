package DBS;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


import java.sql.*;



public class JavaSqlExample {
	
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
public static void main(String args[]) {

    try {
      // Loads the class "oracle.jdbc.driver.OracleDriver" into the memory
      Class.forName("oracle.jdbc.driver.OracleDriver");

      // Connection details
      String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
      String user = "a11825950";
      String pass = "dbs20";

      // Establish a connection to the database
      Connection con = DriverManager.getConnection(database, user, pass);
      Statement stmt = con.createStatement();


      
     String[] brandNames = new String[] {"Nike", "Adidas", "Converse", "Balenciaga", "New Balance", "Gucci", "Puma", "Vans", "Air Jordan", "Reebok"};
     String[] shoesNames = new String[] {"Yeezy", "Runners", "Lowfoot", "Highheel", "5S", "Triple A", "Rex", "Max", "Simple 2", "Elite"};
     String[] condition = new String[] {"brand new", "worn once", "worn but well", "a little bit worn", "very bad", "10/10", "perfect"};
     String[] endDesc = new String[] {"open to offers", "no offers", "can make a discount", "no discounts"};
      
      
      String[] adresses = new String[] {"USA", "Austira", "France", "Germany", "Russia"};
      
      // -- Profile CSV files
      ArrayList<String> nicknames = new ArrayList<>();
     //parsing a CSV file into Scanner class constructor  
      Scanner sc = new Scanner(new File("D:\\Downloads\\csv Profiles\\20210120170111_5089q.csv"));  
      sc.useDelimiter(",");   //sets the delimiter pattern  
      while (sc.hasNext())  //returns a boolean value  
      {  
     nicknames.add(sc.next());  //find and returns the next complete token from this scanner  
      }   
      sc.close();  //closes the scanner  
      
   
        
        
        
         
      
  
          
          ArrayList<String> profileKeys = new ArrayList<>();
          
          Random rand = new Random();
          

     // Insert into Profiles
         PreparedStatement insertSql1 = con.prepareStatement("INSERT INTO Profile (Name, Adress, Shoe_Size) VALUES (?, ?, ?)");
       
	     for(int i = 0; i < 2000; i++) {
	    	 try {
	    	       
	    	      
	    	       int r = rand.nextInt(nicknames.size());
	    	        
	    	       String namevar = nicknames.get(r).toLowerCase().substring(0, 5) + rand.nextInt(100); 
	    	      
	    	       if(profileKeys.contains(namevar)) {i--; continue;}
	    	       profileKeys.add(namevar);
	    	       insertSql1.setString(1, namevar);
	    	       insertSql1.setString(2, adresses[rand.nextInt(adresses.length)]);
	    	       insertSql1.setInt(3, (int) (Math.random() * (45 - 37 + 1) + 37));
	    //executeUpdate Method: Executes the SQL statement, which can be an INSERT, UPDATE, or DELETE statement
	    	       int rowsAffected = insertSql1.executeUpdate();
	    	        
	    	     } catch (Exception e) {
	    	       System.err.println("Error while executing INSERT INTO statement Profile: " + e.getMessage());
	    	     }
	    	  
	      }
       
	   
      
      ArrayList<String> followPairs = new ArrayList<>();
      PreparedStatement insertSql2 = con.prepareStatement("INSERT INTO Follow (Profile_Name1, Profile_Name2) VALUES (?, ?)");
      for(int i = 0; i <= 1200; i++) {
    	  try {
    		  
    	        
    	        int random1 = rand.nextInt(profileKeys.size());
    	        int random2 = rand.nextInt(profileKeys.size());
    	        if (random1 == random2) {i--; continue;}
    	        String name1 = profileKeys.get(random1);
    	        String name2 = profileKeys.get(random2);
    	        if(followPairs.contains(name1) && followPairs.contains(name2)) {i--; continue;}
    	        followPairs.add(name1);
    	        followPairs.add(name2);
    	       
    	        insertSql2.setString(1, name1);
    	        insertSql2.setString(2, name2);
    	    
    	//executeUpdate Method: Executes the SQL statement, which can be an INSERT, UPDATE, or DELETE statement
    	        int rowsAffected = insertSql2.executeUpdate();
    	       
    	      } catch (Exception e) {
    	        System.err.println("Error while executing INSERT INTO statement: Follow " + e.getMessage());
    	      }
      }
      
      
      
      ArrayList<String> profileKeysUsedItem = new ArrayList<>();
      Map<Integer, String> nameItem= new HashMap<>();
      
      
      PreparedStatement insertSql3 = con.prepareStatement("INSERT INTO Item (Name, Description, Price, Seller_Name) VALUES (?, ?, ?, ?)");
      
      for(int i = 1; i <= 1500; i++) {
    	  try {
    		  
    	        
    	       
    	        String sname = brandNames[rand.nextInt(brandNames.length)];
    	        String sname2 = shoesNames[rand.nextInt(shoesNames.length)];
    	        String scondition = condition[rand.nextInt(condition.length)];
    	        String sdesc = endDesc[rand.nextInt(endDesc.length)];
    	        String sellerName = profileKeys.get(rand.nextInt(profileKeys.size()));
    	        insertSql3.setString(1, sname + sname2);
    	        insertSql3.setString(2, sdesc);
    	        insertSql3.setInt(3, randBetween(100, 1500));
    	        insertSql3.setString(4, sellerName);
    	        profileKeysUsedItem.add(sellerName);
    	        nameItem.put(i, sellerName);
    	        
    	        //System.out.println(sname + ", " +  sname2 + ", " + scondition + ", " + sdesc + ", " + sellerName);
    	        
    	//executeUpdate Method: Executes the SQL statement, which can be an INSERT, UPDATE, or DELETE statement
    	        int rowsAffected = insertSql3.executeUpdate();
    	      } catch (Exception e) {
    	        System.err.println("Error while executing INSERT INTO statement: Item" + e.getMessage());
    	      }
      }
      
      String[] payNames = {"Paypal", "Skrill", "Yandex", "Qiwi", "Electra"};
      ArrayList<String> profileKeysUsedPay = new ArrayList<>();
      ArrayList<Integer> cardNum = new ArrayList<>();
      Map<String, Integer> nameCardNum  = new HashMap();
      PreparedStatement insertSql4 = con.prepareStatement("INSERT INTO Paying_Account (Name, Profile_Name, Card_Number, Card_Name, Card_Code) VALUES (?, ?, ?, ?, ?)");
      
      
      for(int i = 0; i <= 1005; i++) {
    	  try {
    	 
    		  	int cardnum2 = randBetween(1000, 9999);
    		  	int cardCode1 = randBetween(100, 999);
    		  	if(cardNum.contains(cardnum2)) {i--; continue;}
    		  	
    		  	
    		  	String name1 = profileKeys.get(rand.nextInt(profileKeys.size()));
    		  	if(profileKeysUsedPay.contains(name1)) {i--; continue;}
    		  	//System.out.println("I am here");
    		  	String payName1 = payNames[rand.nextInt(payNames.length)];
    	        
    	      
    	       
    		  	//System.out.println(payName1);
    	   
    	       
    	        
    	        insertSql4.setString(1, payName1);
    	        insertSql4.setString(2, name1);
    	       
    	        insertSql4.setInt(3, cardnum2);
    	        insertSql4.setString(4, name1);
    	        insertSql4.setInt(5, cardCode1);
    	        nameCardNum.put(name1, cardnum2);
    	        cardNum.add(cardnum2);
    	        profileKeysUsedPay.add(name1);
    	        
    	//executeUpdate Method: Executes the SQL statement, which can be an INSERT, UPDATE, or DELETE statement
    	        int rowsAffected = insertSql4.executeUpdate();
    	      } catch (Exception e) {
    	        System.err.println("Error while executing INSERT INTO statement: Paying Acc" + e.getMessage());
    	      }
      }
     
      
      ArrayList<Integer> usedID = new ArrayList<>();
      ArrayList<Integer> usedCardNum = new ArrayList<>();
      PreparedStatement insertSql5= con.prepareStatement("INSERT INTO Buy_Item (ItemID, Seller_Name, Buyer_Name, Card_Number) VALUES (?, ?, ?, ?)");
      
      for(int i = 1; i <= 1000; i++) {  	
    	  try {		
    		  int nid = i;
    		  
	  		  	if(usedID.contains(nid)) {i--; continue;}
	  		  
	  		  	String name1 = nameItem.get(nid);
	  		  	String name2 = profileKeys.get(rand.nextInt(profileKeys.size()));
	  		  	if (name1.equals(name2)) {i--;continue;}
	  		 
	  		  	if(!nameCardNum.containsKey(name2)) {i--; continue;}
	  		  	int cardNum1 = nameCardNum.get(name2);
	  		  	
	  		  	
    	        insertSql5.setInt(1, nid);
    	        insertSql5.setString(2, name1);
    	        insertSql5.setString(3, name2);
    	        insertSql5.setInt(4, cardNum1);
    	        	       
    	        usedID.add(nid);
    	        usedCardNum.add(cardNum1);
    	        
    	//executeUpdate Method: Executes the SQL statement, which can be an INSERT, UPDATE, or DELETE statement
    	        int rowsAffected = insertSql5.executeUpdate();
    	      } catch (Exception e) {
    	    	
    	        System.err.println("Error while executing INSERT INTO statement: but Iyem" + e.getMessage());
    	      }
      }
      
      
     /*
      try {
        String insertSql = "INSERT INTO Profile VALUES ('Max', 'USA, Miami, Dubstreet 21', 42)";
     
//executeUpdate Method: Executes the SQL statement, which can be an INSERT, UPDATE, or DELETE statement
        int rowsAffected = stmt.executeUpdate(insertSql);
      } catch (Exception e) {
        System.err.println("Error while executing INSERT INTO statement: " + e.getMessage());
      }
      */
     
// Check number of datasets in person table
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Profile");
      if (rs.next()) {
        int count = rs.getInt(1);
        System.out.println("Number of datasets in Profile: " + count);
     }
      ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM Item");
      if (rs1.next()) {
        int count1 = rs1.getInt(1);
        System.out.println("Number of datasets in Item: " + count1);
     }
      ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM Paying_Account");
      if (rs2.next()) {
        int count2 = rs2.getInt(1);
        System.out.println("Number of datasets in Paying_Account: " + count2);
     }
      ResultSet rs3 = stmt.executeQuery("SELECT COUNT(*) FROM Follow");
      if (rs3.next()) {
        int count3 = rs3.getInt(1);
        System.out.println("Number of datasets in Follow: " + count3);
     }
      ResultSet rs4 = stmt.executeQuery("SELECT COUNT(*) FROM Buy_Item");
      if (rs4.next()) {
        int count4 = rs4.getInt(1);
        System.out.println("Number of datasets in Buy_Item: " + count4);
     }
     

     // Clean up connections
      rs.close();
      stmt.close();
      con.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
