/**
 * 
 */
package Transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import components._JInternalFrame;
import interfaces._GUI;

/**
 * 
 */
public class PettyCash extends _JInternalFrame implements _GUI, ActionListener {

	private static final long serialVersionUID = -4306362386582966144L;

	private String title = "Petty Cash";
	private JPanel pnlMain;
	private JPanel pnlMainNorth;
	private JPanel pnlMainCenter;
	private JPanel pnlMainSouth;
	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);

	/**
	 * 
	 */
	public PettyCash() {
		initGUI();
	}

	/**
	 * @param title
	 */
	public PettyCash(String title) {
		super(title);
		initGUI();
	}

	/**
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public PettyCash(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		initGUI();
	}

	@Override
	public void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		this.setSize(SIZE);
		this.setPreferredSize(new Dimension(935, 538));
		{
			pnlMain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				pnlMainNorth = new JPanel(new BorderLayout(5, 5));
				pnlMain.add(pnlMainNorth, BorderLayout.NORTH);
				pnlMainNorth.setPreferredSize(new Dimension(0, 250));
				pnlMainNorth.setBorder(lineBorder);
			}
		}
	}
}
