package loginandsignupcontroller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class LogoutComposer extends SelectorComposer<Component>{
	
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//		String sess =(String) Sessions.getCurrent().getAttribute("currentUser");
//		if(sess == null || sess.equals("")) {
//			Executions.sendRedirect("index.zul");
//		}
//	}
	
	@Listen("onClick=#logoutBtn")
	public void logout() {
		Sessions.getCurrent().removeAttribute("currentUser");
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect("index.zul");
	}
}
