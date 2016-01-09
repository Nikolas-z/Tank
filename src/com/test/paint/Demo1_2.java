package com.test.paint;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Demo1_2 extends JFrame {

	MyPanel mp=null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo1_2 demo=new Demo1_2();
	}
	
	public Demo1_2 ()
	{ 
		
		mp=new MyPanel ();
		this.add(mp);
		
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}


class MyPanel extends JPanel implements KeyListener
{
	int x=10;
	int y=10;
	public void paint (Graphics g)
	{
		
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN) 
		{
			y++;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			y--;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			x--;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			x++;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}