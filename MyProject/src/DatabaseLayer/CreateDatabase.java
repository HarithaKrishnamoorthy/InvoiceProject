package DatabaseLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDatabase {
	public String number=null;
	private String date=null;
	private String customer =null;
	private String address=" ";
	private String dollar=null;
	PreparedStatement pstatement=null;
	Connection con = null;
	public int check = 0;
	public void Invoice() throws Exception
	{
		 FileReader fr=new FileReader("D:/SampleText.txt");
		 BufferedReader br=new BufferedReader(fr);
		 String s;
		 try{
			 System.out.println("jdbc Connection :");
			 con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/invoice","root","haritha"); 
			 System.out.println(con);
			 while ((s=br.readLine())!=null)
			 {
				 int indexfound1=s.indexOf("Invoice No");
				 if (indexfound1>-1)
				 {
					 number=br.readLine();
				 }
				 int indexfound2=s.indexOf("Invoice Date");
				 if (indexfound2>-1)
				 {  
					 date=br.readLine();
				 }
				 int indexfound3= s.indexOf("Customer P.O.");
				 if (indexfound3>-1)
				 {
					 customer=br.readLine();
				 }
				 int addrStart=s.indexOf("Sold To");
				 if(addrStart>-1){
					 for(int i=0;i<3;i++)
					 {
						 address=address+ br.readLine()+",";
						 
					 }
				 }
				 int amount= s.indexOf("$");
				 
				 if(amount>-1)
				 {
					 dollar=s.toString();
					
				 }
				 }
			 
				 String query = "INSERT INTO invoice (Invoice_No, Invoice_Date, Customer_PO, Address, Amount , Status)  VALUES (?, ?, ?, ?, ?, ?)";
				 pstatement=con.prepareStatement(query);
			 	 pstatement.setString(1,number);
			 	 pstatement.setString(2,date);
			 	 pstatement.setString(3,customer);
			 	 pstatement.setString(4,address);
			 	 pstatement.setString(5,dollar);
			 	 pstatement.setString(6,"Not Approved");
			 	 pstatement.execute();
			 	 address=" ";
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally{
		    try{
		                 
		                 
		        if(br != null){
		        	br.close();
		         }
		         if(fr !=null){
		            fr.close();
		         }
		          if(pstatement != null){
			         pstatement.close(); 
			      }
		        }
	
		      catch(Exception e2){
		    	  e2.printStackTrace();
	               System.out.println("Connection failed!!!");
		      }
		}	   
	}
	
	public void UpdateInvoice(String invoiceNo) throws InterruptedException
	{  PreparedStatement pstatement1=null;
	   check=0;
		try {
			pstatement1=con.prepareCall("update invoice set status=?  where Invoice_No = ?");
			
			pstatement1.setString(1,"Approved");
			pstatement1.setString(2, invoiceNo);
			check = (pstatement1.executeUpdate());
			System.out.println("Invoice is updated");
			
	    } 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invoice is not updated");
		}
		 
		finally{
			try{
				 			
				if(pstatement1 != null){
           	    pstatement1.close();
            }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}
}
