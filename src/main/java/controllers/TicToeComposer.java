package controllers;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

public class TicToeComposer extends SelectorComposer<Window> {
	@Wire
	Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
	Boolean flag = true;

	@Listen("onClick=button")
	public void gameOn(Event e) {
		Button b = (Button) (e.getTarget());

		b.setLabel(flag ? "X" : "O");
		flag = !flag;
		b.setDisabled(true);

		if (b1.getLabel() == b2.getLabel() && b1.getLabel() == b3.getLabel() && b1.getLabel() != ""
				|| b4.getLabel() == b5.getLabel() && b4.getLabel() == b6.getLabel() && b4.getLabel() != ""
				|| b7.getLabel() == b8.getLabel() && b7.getLabel() == b9.getLabel() && b7.getLabel() != ""
				|| b1.getLabel() == b4.getLabel() && b1.getLabel() == b7.getLabel() && b1.getLabel() != ""
				|| b2.getLabel() == b5.getLabel() && b2.getLabel() == b8.getLabel() && b2.getLabel() != ""
				|| b3.getLabel() == b6.getLabel() && b3.getLabel() == b9.getLabel() && b3.getLabel() != ""
				|| b1.getLabel() == b5.getLabel() && b1.getLabel() == b9.getLabel() && b1.getLabel() != ""
				|| b3.getLabel() == b5.getLabel() && b3.getLabel() == b7.getLabel() && b3.getLabel() != "") {
			alert(b.getLabel() + " Win!");
		}
	}
	
	public void doAfterComposer(Window com) throws Exception {
		super.doAfterCompose(com);
		com.appendChild(new Button("button1"));
	}
}
