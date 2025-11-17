package loginandsignupcontroller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class LoginComposer extends SelectorComposer<Component>{
	@Wire private Textbox user,pass;
	
	@Listen("onClick=#login")
	public void loginUser(){
		Executions.sendRedirect("login.zul");
	}
	
	@Listen("onClick=#btn")
	public void HOMEPAR() {
		if(user.getValue().equals("harish") && pass.getValue().equals("1234")) {
			Executions.getCurrent().getSession().setAttribute("currentUser", user.getValue());
			Executions.sendRedirect("home.zul");
		}
		else {
			Messagebox.show("Invalid user");
		}
	}
}
