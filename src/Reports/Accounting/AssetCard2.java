package Reports.Accounting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components._JInternalFrame;
import interfaces._GUI;

public class AssetCard2 extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = 1L;
	public static String title = "Asset Card";
	public static Dimension framesize= new Dimension(450, 150);
	private JPanel pnlMain;
	
	public AssetCard2(){
		super(title, false, true, true, true);
		initGUI();
		
	}
	public AssetCard2(String title) {
		super(title, true, true, true, true);
		initGUI();
	}

	public AssetCard2(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(framesize);
		this.setPreferredSize(framesize);
		this.setMinimumSize(framesize);
		this.setLayout(new BorderLayout(5,5));
		{
			pnlMain = new JPanel(new GridBagLayout());
			this.add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
			{
			}
		}

	}

}
