package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class LoginComposer extends SelectorComposer<Window>{
	
	@Wire private Textbox tUserName , tPassword;
	@Wire Button submit ,reset;
	
	@Listen("onClick=button")
	public void validate(Event e) {
		Button btn = (Button)e.getTarget();
		String btnId = btn.getId();
		
		if(btnId.equals("submit")) {
			//code for login user
			try{
				
				Class.forName("com.mysql.cj.jdbc.Driver");
		    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Nayan@2025");
		    	
		    	PreparedStatement ps = con.prepareStatement("select * from employee where FullName = ? and password =?");
		    	ps.setString(1,tUserName.getText());
		    	ps.setString(2,tPassword.getText());
		    	ResultSet rs = ps.executeQuery();
		    	
		    	if(rs.next()){
		    		alert("Login successful");
		    		Executions.sendRedirect("Home.zul");
		    	}
		    	else{ 
		    		alert("Unauthorized Useccr");
		    	}
		    	
		    	}
		    catch(Exception e1){
		  		e1.printStackTrace();
		  		System.out.println(e1.getMessage());
		  	}
		}
		if(btnId.equals("reset")) {
			tUserName.setText("");
			tPassword.setText("");
		}
	}

}
