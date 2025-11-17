package loginandsignupcontroller;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;

public class Authentication implements Initiator{

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		String sess =(String) Sessions.getCurrent().getAttribute("currentUser");
		if(sess == null || sess.equals("")) {	
			Executions.sendRedirect("index.zul");
		}
	}
}

