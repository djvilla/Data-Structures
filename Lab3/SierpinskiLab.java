/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 03
Purpose: The purpose of this lab is to generate Sierpinski's Carpet. Orginally this was done in recursion. However, our objective is
to replace these recursion calls with stacks.
TA: Zakia Al Kadri
Last Modified: September 29 2017
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.System;
 
@SuppressWarnings("serial")
public class SierpinskiLab extends JFrame {
 
    private Graphics2D g2d;
 
    public SierpinskiLab() {
        super("Sierpinski Lab");
 
        getContentPane().setBackground(Color.BLACK);
        setSize(729, 729);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    void drawRectangles(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setPaint(Color.GRAY);
		long startTime = System.currentTimeMillis();
        drawGasket(0, 0, 729);
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime));
    }
	
	//This drawGasket method utalizes statcks instead of recursion calls
	public void drawGasket(int x, int y, int side) {
        Stack squareDemensions = new Stack();
		//Put the first values into the stack
		rectangle Square = new rectangle(x, y, side);
		squareDemensions.push(Square);
 
		//Do not leave the while loop until the stack is empty
        while(squareDemensions.isEmpty() != true) {
			int sub = Square.side / 3;
			//Draw center square
			g2d.fill(new Rectangle2D.Double(Square.x + sub, Square.y + sub, sub - 1, sub - 1));
			if(sub >= 3){
				//Draw 8 surrounding squares
				for (int i = 0; i < 3; i++){
					for (int j = 0; j < 3; j++){
						if (j!=1 || i != 1){
							//Generate a new square and push it to the stack while using the Square1's varable
							rectangle Square2 = new rectangle(Square.x + i * sub, Square.y + j * sub, sub);
							squareDemensions.push(Square2);
							//Clear square to make sure the same values are not passing into the stack
							Square2 = null;
						}
					}
				}
			}
			//Clear the square and make the top of the stack the new square to work with.
			Square = null;
			Square = squareDemensions.pop();
        }
    }
	
    /*public void drawGasket(int x, int y, int side) {
        int sub = side / 3; 
 
        //Draw center square
        g2d.fill(new Rectangle2D.Double(x + sub, y + sub, sub - 1, sub - 1));
 
        if(sub >= 3) {
            //Draw 8 surrounding squares
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (j!=1 || i != 1)
                        drawGasket(x + i * sub, y + j * sub, sub);
                }
            }
        }
    }*/
	
 
    public void paint(Graphics g) {
        super.paint(g);
        drawRectangles(g);
    }
 
    public static void main(String[] args) throws Exception {
 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SierpinskiLab().setVisible(true);
            }
        });
    }
 
}