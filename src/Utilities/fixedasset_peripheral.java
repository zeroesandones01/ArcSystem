package Utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import components._JInternalFrame;
import interfaces._GUI;

public class fixedasset_peripheral extends _JInternalFrame implements _GUI, ActionListener {


	private static final long serialVersionUID = 1L;
	static String title = "Asset Peripheral";
	public static Dimension frameSize = new Dimension(700, 500);

	public fixedasset_peripheral() {
		super(title, true, true, true, true);
		initGUI();
	}

	public fixedasset_peripheral(String title) {
		super(title);
	}

	public fixedasset_peripheral(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		this.setLayout(new BorderLayout());
		

	}

}
