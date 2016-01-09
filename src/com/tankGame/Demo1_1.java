package com.tankGame;
/*
 * 1.画出敌我坦克；
 * 2.我的坦克可以通过按键上下左右移动；敌人的坦克自由的移动；（不超过边界）
 * 3.我的坦克可以按键发射子弹，子弹可以连发；
 * 4.敌人的坦克可以发射子弹，子弹最多存在三个，每0.5*n秒发射一颗子弹；
 * 5.当敌我坦克被子弹击中的时候，产生爆炸效果，并消失；
 * 6.防止敌人坦克重叠运动；
 * 7.可以分关；
 * 8.可以暂停和继续；
 * 9.可以记录和保存；
 */
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;



public class Demo1_1 extends JFrame implements ActionListener {
	MyPanel mp;
	MyStartPanel msp;
	JMenuBar jmb= new JMenuBar();
	JMenu jm=null;
	JMenuItem jmi=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo1_1 demoe=new Demo1_1();
	}
	
	public Demo1_1()//构造函数
	{
		mp=new MyPanel(0);
		msp=new MyStartPanel();
		jm=new JMenu("菜单(j)");
		jm.setMnemonic('j');
		jm.addActionListener(this);
		jm.setActionCommand("start");
		
		jmi=new JMenuItem("新游戏(n)");
		jmi.setMnemonic('n');
		jmi.addActionListener(this);
		jmi.setActionCommand("start");
		
		jmi3=new JMenuItem("继续游戏(c)");
		jmi3.setMnemonic('c');
		jmi3.addActionListener(this);
		jmi3.setActionCommand("continue");
		
		jmi1=new JMenuItem("保存(s)");
		jmi1.setMnemonic('s');
		jmi1.addActionListener(this);
		jmi1.setActionCommand("save");
		
		
		jmi2=new JMenuItem("退出游戏(e)");
		jmi2.setMnemonic('e');
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		
		
		jm.add(jmi);
		jm.add(jmi3);
		jm.add(jmi1);
		jm.add(jmi2);
		jmb.add(jm);
		this.setJMenuBar(jmb);
		
//		Thread t= new Thread(mp);
//		t.start();
		
//		this.add(mp);	
//		this.addKeyListener(mp);
		this.add(msp);

		this.setSize(600, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("start"))//新游戏
		{
			this.remove(msp);
			this.remove(mp);
			mp=new MyPanel(0);
			Thread t= new Thread(mp);
			try {
				t.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			t.start();
			
			this.add(mp);	
			this.addKeyListener(mp);
			this.setVisible(true);
		}
		
		if(e.getActionCommand().equals("exit"))//退出游戏
		{
			//保存数据..
			Record.setEts(mp.ets);
			Record.keepRecord();
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("save"))//保存
		{
			//保存坦克位置等
			Record.setEts(mp.ets);
			Record.keepRecord();
		}
		
		if(e.getActionCommand().equals("continue"))//继续游戏
		{
			//继续游戏
			this.remove(msp);
			mp=new MyPanel(1);
			Thread t= new Thread(mp);
			try {
				t.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			t.start();
			
			this.add(mp);	
			this.addKeyListener(mp);
			this.setVisible(true);
			
		}
	}

}


//提示作用
class MyStartPanel extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		Font f=new Font("行楷",Font.BOLD,20);
		g.setFont(f);
		g.setColor(Color.yellow);
		g.drawString("Gate1-1", 160, 130);
		
	}
}


class MyPanel extends JPanel implements KeyListener,Runnable
{
	static int pause=2;//暂停标记
	static int tempetspeed=0;//记录敌方坦克速度值
	static int tempsspeed=0;//记录子弹的速度值
	MyTank mt=null;//我的坦克；
	Image image1=null;
	Image image2=null;
	Image image3=null;
	

	Vector<EnemyTank> ets=new Vector<EnemyTank>();//敌方坦克的集合
	Vector<Boom> bs = new Vector<Boom>();//爆炸效果
	
	
	int enSize=4;//地方坦克数量
	
	public MyPanel(int flag)
	{
			
		
		mt=new MyTank (250,200,2,1,0);//我的坦克初始化
	//		Boom b=new Boom(mt.x+10,mt.y);//为了在击中第一个坦克之前存一个爆炸，一个坦克消失的同时才会有画面
	//		bs.add(b);
		if(flag==0)
		{
			for(int i=0;i<enSize;i++)//敌方坦克初始化
			{
				EnemyTank et=new EnemyTank((i+1)*50,0,1,0,2);
				et.setEts(ets);//将panel上的敌人坦克的向量给当前坦克；
				ets.add(et);			
				Thread t=new Thread(et);
				t.start();
				et.shotEnemy();
			}
		}
		if(flag==1)
		{
			Vector<EnemyTank> etss =  Record.getRecord();
			//System.out.println(etss.size());
			for(int i=0;i<etss.size();i++)
			{
				EnemyTank etm=etss.get(i);
				EnemyTank et=new EnemyTank(etm.x,etm.y,etm.speed,etm.color,etm.direct);
				
				et.setEts(ets);//将panel上的敌人坦克的向量给当前坦克；
				ets.add(et);			
				Thread t=new Thread(et);
				t.start();
				et.shotEnemy();
			}
		}
		//这个方法可以直接避免打死第一个坦克没有出现爆炸效果的问题
		try {
			image1=ImageIO.read(new File("image4.png"));
			image2=ImageIO.read(new File("image2.png"));
			image3=ImageIO.read(new File("image3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		//爆炸效果初始化(图形载入)
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image4.png"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image2.png"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image3.png"));
	}
	public void paint(Graphics g)
	{

		super.paint(g);
		
		//提示信息
		this.drawTank(40, 310, g, 0, 0);
		g.setColor(Color.black);
		g.drawString("x"+Record.getEnemyTankNum(), 65, 340);
		this.drawTank(90,310, g, 0, 1);
		g.setColor(Color.black);
		g.drawString("x"+Record.getMyTankLife(), 115, 340);
		
		//成绩
		Font f = new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("击杀坦克数", 410, 40);
		this.drawTank(410, 50, g, 0, 0);
		g.setColor(Color.black);
		g.drawString("x"+Record.getDeadTankNum(), 435, 75);
		
		g.fillRect(0, 0, 400, 300);//战场大小
		
		// 画自己的坦克
		if(mt.isLive==true){
			this.drawTank(mt.getX(), mt.getY(), g, mt.getDirect(), mt.getColor());
			}
		
		
		for(int i=0;i<ets.size();i++)//画敌方坦克
		{
			EnemyTank et=ets.get(i);
			if(et.isLive==true)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.getColor());			
			}
			if(et.isLive==false)
			{
				ets.remove(et);				
			}
		}
		
		
		for(int i=0;i<mt.ss.size();i++)//画自己的子弹
		{
			shot mts= mt.ss.get(i);
			if(mts!=null&&mts.isLive==true)
			{
				g.fill3DRect(mts.x, mts.y, 2, 2, false);
			}
			if(mts.isLive==false)
			{
				mt.ss.remove(mts);
			}
		}
		
		
		//画敌方坦克子弹
		for(int i=0;i<ets.size();i++){
			EnemyTank et=ets.get(i);
			for(int j=0;j<et.ss.size();j++)
			{
				shot es=et.ss.get(j);
				if(es.isLive==true)
				{
					g.fill3DRect(es.x, es.y, 2, 2, false);
				}
				if(es.isLive==false)
				{
					et.ss.remove(es);
				}
			}
		}

		for(int i=0;i<bs.size();i++)//绘制爆炸画面
		{
			Boom b=bs.get(i);
			if(b.isLive==true){
			if(b.life>10)
			{g.drawImage(image1, b.x, b.y, 30, 30, this);}
			else if (b.life>5)
			{g.drawImage(image2, b.x, b.y, 30, 30, this);}
			else
			{g.drawImage(image3, b.x, b.y, 30, 30, this);}}
			b.lifedown();
			
			if(b.isLive==false)
			{bs.remove(b);}
				
		}
		
	
//		Image im=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image1.jpg"));
//		g.drawImage(im, 40, 40, 40, 100, this);
//		g.setColor(Color.black);
//		g.setFont(new Font("华文彩云",Font.BOLD,20));
//		g.drawString("天龙八部", 40, 40);
	}
	public void drawTank(int x,int y,Graphics g,int direct ,int type)
	{
		
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;

		}
		
		switch(direct)
		{
		case 0:
			
			g.fill3DRect(x, y+5, 5, 30,false);
			g.fill3DRect(x+5, y+10, 10, 20,false);
			g.fill3DRect(x+15, y+5, 5, 30,false);
			g.fillOval(x+5, y+15, 10, 10);
			g.drawLine(x+10,y+15 , x+10, y);
		
			break;
		case 1:
			g.fill3DRect(x-5, y+5, 30, 5, false);
			g.fill3DRect(x, y+10, 20, 10,false);
			g.fill3DRect(x-5, y+20, 30,5,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10,y+15 , x+30, y+15);
			break;
		case 2:
			g.fill3DRect(x, y+5, 5, 30,false);
			g.fill3DRect(x+5, y+10, 10, 20,false);
			g.fill3DRect(x+15, y+5, 5, 30,false);
			g.fillOval(x+5, y+15, 10, 10);
			g.drawLine(x+10,y+15 , x+10, y+40);
			break;
		case 3:
			g.fill3DRect(x-5, y+5, 30, 5, false);
			g.fill3DRect(x, y+10, 20, 10,false);
			g.fill3DRect(x-5, y+20, 30,5,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10,y+15 , x-10, y+15);
			break;
		
		}
	}
	
	
	//子弹是否击中
	public void hitTank(shot s,Tank t)
	{
		switch(t.direct)
		{	
			case 0:
			case 2:
				if(s.x>=t.x&&s.x<=t.x+20&&s.y<=t.y+5&&s.y>=t.y+35)
				{
					//子弹死亡
					s.isLive=false;
					//坦克死亡
					t.isLive=false;
					//发生爆炸
					Boom b=new Boom(t.x,t.y+5);
					bs.add(b);
					
				}
				//break;
			case 1:
			case 3:
				if(s.x>=t.x-5&&s.x<=t.x+25&&s.y>=t.y+5&&s.y<=t.y+25)
				{
					//子弹死亡
					s.isLive=false;
					//坦克死亡
					t.isLive=false;
					Boom b=new Boom(t.x-5,t.y+5);
					bs.add(b);
				}
				//break;
		}
	}
	
	//判断子弹是否击中敌方坦克
	public void hitEnemyTank()
	{
		for(int i=0;i<mt.ss.size();i++)
		{
			shot ms = mt.ss.get(i);
			if(ms.isLive==true)
			{
				for(int j=0;j<ets.size();j++)
				{
					EnemyTank et=this.ets.get(j);
					if(et.isLive==true)
					{
						
						this.hitTank(ms, et);
						//坦克数量减少
						if(!et.isLive)
						{
							Record.ReduceETN();
							Record.AddDTN();
						}
					}
				}
			}
		}
	}
	
	
	//判断敌方子弹是否击中自己
	public void hitMyTank(){
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive==true){
				for(int j=0;j<et.ss.size();j++)
				{
					shot es=et.ss.get(j);
					if(es.isLive)
					{
						if(mt.isLive)
						{
							this.hitTank(es, mt);
							if(!mt.isLive)
								Record.ReduceMTL();
						}
					}
				}
			}
			
		}
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
			if(mt.y+40<300){
			this.mt.setDirect(2);
			//this.mt.moveDown();
			mt.moveDown();}
			
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{  		
			if(mt.y-5>0){
			this.mt.setDirect(0);
			this.mt.moveUp();}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(mt.x-10>0){
			this.mt.setDirect(3);
			this.mt.moveLeft();}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(mt.x+35<400){
			this.mt.setDirect(1);
			this.mt.moveRight();}
		}
		if(e.getKeyCode()==KeyEvent.VK_X)
		{
			if(this.mt.ss.size()<5)
			{
				this.mt.shotEnemy();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)//暂停继续
		{
			for(int i=0;i<this.ets.size();i++)
			{
				if(pause%2==0)
				{
					EnemyTank et=ets.get(i);
					et.setShotsize(0);
					//取出个坦克的速度；
					int etsp=et.speed;
					tempetspeed=etsp;
					et.setSpeed(0);
					for(int j=0;j<et.ss.size();j++)
					{
						shot s=et.ss.get(j);
						int etssp=s.speed;
						tempsspeed=etssp;
						s.setSpeed(0);
					}
				}
				else
				{
					EnemyTank et=ets.get(i);
					et.setShotsize(3);
					et.setSpeed(tempetspeed);
					for(int j=0;j<et.ss.size();j++)
					{
						shot s=et.ss.get(j);
						s.setSpeed(tempsspeed);
						
					}
				}
			}
			pause++;
		}
		//this.repaint(); 
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//判断子弹是否击中敌方坦克
			this.hitEnemyTank();
			//判断子弹是否击中我方坦克
			this.hitMyTank();

			
			this.repaint();
		}
	}
}


 