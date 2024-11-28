package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ArcSysLogin extends JFrame {

//	public ArcSysLogin() throws HeadlessException {
//		// TODO Auto-generated constructor stub
//	}

	public ArcSysLogin(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public ArcSysLogin(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public ArcSysLogin(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public ArcSysLogin() {
		setResizable(false);
        // Set the frame properties
        setTitle("Custom Login Frame");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true); // Remove the default window decorations
        setShape(new RoundRectangle2D.Double(0, 0, 400, 300, 50, 50)); // Rounded edges

        // Create a custom panel with rounded edges and background color
        JPanel titleBar = new JPanel();
        titleBar.setBackground(Color.BLUE);
        titleBar.setPreferredSize(new Dimension(this.getWidth(), 30));
        titleBar.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Custom Title Bar");
        titleLabel.setForeground(Color.WHITE);
        titleBar.add(titleLabel, BorderLayout.CENTER);

        // Add a close button to the title bar
        JButton closeButton = new JButton("X");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.RED);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> this.dispose());
        titleBar.add(closeButton, BorderLayout.EAST);
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private int pX, pY;

            @Override
            public void mousePressed(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                getContentPane().setLocation(getContentPane().getLocation().x + e.getX() - pX,
                		getContentPane().getLocation().y + e.getY() - pY);
            }
        };
        titleBar.addMouseListener(mouseAdapter);
        titleBar.addMouseMotionListener(mouseAdapter);

        // Add the custom title bar to the frame
        getContentPane().add(titleBar, BorderLayout.NORTH);
        
        CustomPanel panel = new CustomPanel();
        panel.setLayout(null); // Use absolute positioning

        // Create and add components to the panel
        JLabel userLabel = new JLabel("Username:");
        //userLabel.setBounds(50, 50, 100, 30);
        panel.add(userLabel);

        JTextField userText = new JTextField();
        //userText.setBounds(150, 50, 200, 30);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password:");
        //passLabel.setBounds(50, 100, 100, 30);
        panel.add(passLabel);

        JPasswordField passText = new JPasswordField();
        //passText.setBounds(150, 100, 200, 30);
        panel.add(passText);

        JButton loginButton = new JButton("Login");
        //loginButton.setBounds(150, 150, 100, 30);
        panel.add(loginButton);

        // Add the custom panel to the frame
        getContentPane().add(panel);
    }

    // Custom JPanel class with rounded edges and background color
    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(34, 67, 46)); // Set the background color
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50); // Draw a rounded rectangle
        }
    }

}
