package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class SignupComposer extends SelectorComposer<Window> {
	@Wire private Textbox userId,userName,userEmail,password,confirmPassword;
	
	
	@Listen("onClick=#btnSubmit")
	public void insertData() throws WrongValueException, NumberFormatException, SQLException {
		if(!password.getText().equals(confirmPassword.getText())) {
			alert("Password doesn't match");
			return;
		}
		
		if(isPresent(Integer.parseInt(userId.getText()))) {
    		Executions.sendRedirect("login.zul");
    		alert("User already exist ! Please Login");
    		return;
		}
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Nayan@2025");
	    	
	    	String query= "INSERT INTO employee (UserId,FullName,Email,Password) values (?,?,?,?);";
	    	
	    	PreparedStatement ps = con.prepareStatement(query);
	    	ps.setInt(1,Integer.parseInt( userId.getText()));
	    	ps.setString(2, userName.getText());
	    	ps.setString(3, userEmail.getText());
	    	ps.setString(4, password.getText());
	    	
	    	int row  = ps.executeUpdate();
	    	if(row>0) {
	    		Executions.sendRedirect("Home.zul");
	    		alert("Registration Done! Now You can Login");
	    	}
	    	else {
	    		alert("something wrong!");
	    	}
		}	
	    catch(Exception e1){
	  		e1.printStackTrace();
	  		System.out.println(e1.getMessage());
	  	}
	}
	
	@Listen("onClick=#btnReset")
	public void reset() {
		userId.setText(""); 
		userName.setText(""); 
		userEmail.setText(""); 
		password.setText(""); 
		confirmPassword.setText("");
	}
	
	public boolean isPresent(int id) throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Nayan@2025");
	    	
	    	
	    	String query= "select * from employee where UserId = ?;";
	    	PreparedStatement ps = con.prepareStatement(query);
	    	ps.setInt(1, id);
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next()) {
	    		return true;
	    	}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return false;
    	
	}
		
}
