/*
 *  soapUI, copyright (C) 2004-2009 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package Login; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;
import javax.swing.JPanel;

public class Banner extends JPanel
{

          public final static int HORIZONTAL = 0;
          public final static int VERTICAL = 1;
          public final static int DIAGONAL_LEFT = 2;
          public final static int DIAGONAL_RIGHT = 3;
          private int direction = HORIZONTAL;
          private boolean cyclic;
          private int maxLength;
          
          private static Color color1;
          private static Color color2; 

          public Banner() {
                    this( HORIZONTAL );
          }
          
          public Banner(int direction) {
                    super( new BorderLayout() );
                    setOpaque( false );
                    this.direction = direction;
          }
          
          public Banner(LayoutManager layoutManager) {
                    super( layoutManager );
                    setOpaque( false );
                    this.direction = HORIZONTAL;
          }

          public Banner(LayoutManager layoutManager, Color color1, Color color2) {
                    super( layoutManager );
                    this.color1 = color1; 
                    this.color2 = color2; 
                    setOpaque( false );
                    this.direction = HORIZONTAL;
          }
          
          public int getDirection() {
                    return direction;
          }
          
          public void setDirection( int direction ) {
                    this.direction = direction;
          }
          
          public boolean isCyclic() {
                    return cyclic;
          }
          
          public void setCyclic( boolean cyclic ) {
                    this.cyclic = cyclic;
          }
          
          public void setMaxLength( int maxLength ) {
                    this.maxLength = maxLength;
          }
          
          public void paintComponent( Graphics g ) {
                    if (isOpaque()) {
                              super.paintComponent( g );
                              return;
                    }
                    
                    int width = getWidth();
                    int height = getHeight();

                    GradientPaint paint = null;
                    Color sc = color1; 
                    Color ec = color2; 
                    switch( direction ) {
                    case HORIZONTAL :
                    {
                              paint = new GradientPaint( 0, height / 2, sc, width, height / 2, ec, cyclic );
                              break;
                    }
                    case VERTICAL :
                    {
                              paint = new GradientPaint( width / 2, 0, sc, width / 2, maxLength > 0 ? maxLength : height, ec, cyclic );
                              break;
                    }
                    case DIAGONAL_LEFT :
                    {
                              paint = new GradientPaint( 0, 0, sc, width, height, ec, cyclic );
                              break;
                    }
                    case DIAGONAL_RIGHT :
                    {
                              paint = new GradientPaint( width, 0, sc, 0, height, ec, cyclic );
                              break;
                    }
                    }
                    
                    if (paint==null) {
                              throw new RuntimeException( "Invalid direction specified in Banner" );
                    }

                    Graphics2D g2d = ( Graphics2D )g;
                    Paint oldPaint = g2d.getPaint();
                    g2d.setPaint( paint );
                    g2d.fillRect( 0, 0, width, height );
                    g2d.setPaint( oldPaint );
                    super.paintComponent( g );
          }
}
