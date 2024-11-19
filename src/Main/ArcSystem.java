package Main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Functions.FncGlobal;
import Functions.FncLookAndFeel;
import Login.ArcSysLogin;
import Login.ArcSystemLogin2;
import components._JXLoginPane;

public class ArcSystem {

	public static void main(String[] args) {
		//FncGlobal.lpsOut = Functions.LoggedPrintStream.create(System.out);
		//System.setOut(FncGlobal.lpsOut);

		//ArcSystemLogin.showLogin();
		
//		FncLookAndFeel.initialize();
//		FncGlobal.initialize(false);
//
//		/*UIDefaults defaults = UIManager.getLookAndFeelDefaults();
//		for (Enumeration enumm = defaults.keys(); enumm.hasMoreElements();) {
//			Object key = enumm.nextElement();
//			//Object value = defaults.get(key);
//
//			if(key.toString().contains("Table")){
//				//System.out.printf("%s - %s%n", key, UIManager.get(key));https://marketplace.eclipse.org/marketplace-client-intro?mpc_install=3085446
//			}
//		}*/
//		
//		//JOptionPane.showMessageDialog(null, "Cannot login system is under maintenance", "Login", JOptionPane.WARNING_MESSAGE);
//
//		FncGlobal.parentFrame = _JXLoginPane.showLoginFrame(FncGlobal.login);
//		//FncGlobal.parentFrame = JXLoginPane.showLoginFrame(FncGlobal.login);
//		FncGlobal.parentFrame.setTitle(FncGlobal.ORIGINAL_TITLE);
//		FncGlobal.parentFrame.setIconImage(FncLookAndFeel.iconSystem);
//		FncGlobal.parentFrame.pack();
//		FncGlobal.parentFrame.setVisible(true);
		
		SwingUtilities.invokeLater(() -> {
            ArcSysLogin frame = new ArcSysLogin();
            frame.setVisible(true);
        });
		
		
	}
}