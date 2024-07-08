package Accounting.FixedAssets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import DateChooser._JDateChooser;
import Lookup.LookupEvent;
import Lookup.LookupListener;
import Lookup._JLookup;
import components.JTBorderFactory;
import components._JInternalFrame;
import components._JTagLabel;
import interfaces._GUI;

public class addassetperipheral extends _JInternalFrame implements _GUI, ActionListener {


	private static final long serialVersionUID = 1L;
	static String title = "Add Asset Peripherals";
	public static Dimension frameSize = new Dimension(700, 500);
	private _JLookup lookupcomp;
	private _JLookup lookupassetno;
	private _JTagLabel tagcomp;
	private _JTagLabel tagasset_name;
	private JButton btnnew;
	private JButton btncancel;
	private _JDateChooser dateAcquired;
	private JButton btnsave;
	protected String co_name;
	protected String asset_name;
	private JScrollPane scrollfortag;
	private JScrollPane scrolltagged;

	public addassetperipheral() {
		super(title, true, true, true, true);
		initGUI();
	}

	public addassetperipheral(String title) {
		super(title);
	}

	public addassetperipheral(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
	}

	@Override
	public void initGUI() {
		this.setTitle(title);
		this.setSize(frameSize);
		this.setPreferredSize(frameSize);
		this.setLayout(new BorderLayout());
		{
			JPanel pnlmain = new JPanel(new BorderLayout(5, 5));
			getContentPane().add(pnlmain, BorderLayout.CENTER);
			pnlmain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			{
				JPanel pnlnorth = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlnorth,BorderLayout.NORTH);
				pnlnorth.setPreferredSize(new Dimension(0, 80));
				{
					JPanel pnlnorth_west = new JPanel(new GridLayout(3, 1, 3, 3));
					pnlnorth.add(pnlnorth_west, BorderLayout.WEST);
					{
						JLabel lblcomp = new JLabel("Company");
						pnlnorth_west.add(lblcomp);
					}
					{
						JLabel lblassetno = new JLabel("Asset No.");
						pnlnorth_west.add(lblassetno);
					}
					pnlnorth_west.add(Box.createHorizontalBox());
				}
				{
					JPanel pnlnorth_center = new JPanel(new GridLayout(3, 1, 3, 3) );
					pnlnorth.add(pnlnorth_center, BorderLayout.CENTER);
					{
						lookupcomp = new _JLookup(null, "Company", 0);
						pnlnorth_center.add(lookupcomp);
						lookupcomp.setLookupSQL("select '1', 'Acerland'");
						lookupcomp.addLookupListener(new LookupListener() {

							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if(data != null) {
									co_name = (String)data[1];
									
									tagcomp.setTag(co_name);
								}
							}
						});
					}
					{
						lookupassetno = new _JLookup(null, "Asset No.", 0);
						pnlnorth_center.add(lookupassetno);
						lookupassetno.setLookupSQL("select '1', 'Test Asset'");
						lookupassetno.addLookupListener(new LookupListener() {
							public void lookupPerformed(LookupEvent event) {
								Object[] data = ((_JLookup) event.getSource()).getDataSet();
								if(data != null) {
									asset_name = (String)data[1];
									
									tagasset_name.setTag(asset_name);
								}   
							}
						});
					}
					pnlnorth_center.add(Box.createHorizontalBox());
				}
				{
					JPanel pnlnorth_east = new JPanel(new GridLayout(3, 1, 3, 3) );
					pnlnorth.add(pnlnorth_east, BorderLayout.EAST);
					pnlnorth_east.setPreferredSize(new Dimension(470, 0));
					{
						tagcomp = new _JTagLabel("[ ]");
						pnlnorth_east.add(tagcomp);
					}
					{
						tagasset_name = new _JTagLabel("[ ]");
						pnlnorth_east.add(tagasset_name);
					}
					pnlnorth_east.add(Box.createHorizontalBox());
				}
			}
			{
				JPanel pnlcenter = new JPanel(new BorderLayout(5, 5));
				pnlmain.add(pnlcenter, BorderLayout.CENTER);
				pnlcenter.setBorder(JTBorderFactory.createTitleBorder(""));
				{
					JPanel pnltagging = new JPanel(new GridLayout(2, 1, 5, 5));
					pnlcenter.add(pnltagging, BorderLayout.CENTER);
					{
						JPanel pnlfortag = new JPanel(new BorderLayout());
						pnltagging.add(pnlfortag);
						pnlfortag.setBorder(JTBorderFactory.createTitleBorder("For Tagging"));
						{
							scrollfortag = new JScrollPane();
							pnlfortag.add(scrollfortag);
							scrollfortag.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							{
								
							}
						}
					}
					{
						JPanel pnltagged = new JPanel(new BorderLayout());
						pnltagging.add(pnltagged);
						pnltagged.setBorder(JTBorderFactory.createTitleBorder("Tagged Peripheral"));
						{
							scrolltagged = new JScrollPane();
							pnltagged.add(scrolltagged);
							scrolltagged.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							{
								
							}
						}
					}
				}
			}
			{
				JPanel pnlsouth = new JPanel(new GridLayout(1, 5, 5, 5));
				pnlmain.add(pnlsouth, BorderLayout.SOUTH);
				pnlsouth.setPreferredSize(new Dimension(0, 35));
				
				pnlsouth.add(Box.createHorizontalBox());
				{
					btnnew = new JButton("New"); 
					btnnew.setActionCommand("new");
					pnlsouth.add(btnnew);
				}
				{
					btnsave = new JButton("Save");
					btnsave.setActionCommand("save");
					pnlsouth.add(btnsave);
				}
				{
					btncancel = new JButton("Cancel");
					btncancel.setActionCommand("cancel");
					pnlsouth.add(btncancel);
				}
				pnlsouth.add(Box.createHorizontalBox());
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("new")) {
			
		}
		
		if (e.getActionCommand().equals("save")) {
			
		}
		
		if(e.getActionCommand().equals("cancel")) {
			
		}
		
	}
}

