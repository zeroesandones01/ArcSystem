package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.text.Document;

public class CustomJPasswordField extends JPasswordField {

	private static final long serialVersionUID = -4394295881971699118L;
	private Color backgroundColor = Color.GRAY;
    private int radius = 10; // Default radius for rounded corners

    public CustomJPasswordField(int columns) {
        super(columns);
        setOpaque(false); // Make the field non-opaque to paint a custom background
        //setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add some padding
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1)); // Add some padding
    }

    // Setter for background color
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
        repaint(); // Repaint the field with the new color
    }

    // Setter for corner radius
    public void setRadius(int radius) {
        this.radius = radius;
        repaint(); // Repaint the field with the new radius
    }
    
    public void setFontColor(Color color) {
        setForeground(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint the rounded background
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint the border around the rounded background if needed
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
    }

}
