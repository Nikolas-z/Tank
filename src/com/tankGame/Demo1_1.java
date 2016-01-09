package com.tankGame;
/*
 * 1.��������̹�ˣ�
 * 2.�ҵ�̹�˿���ͨ���������������ƶ������˵�̹�����ɵ��ƶ������������߽磩
 * 3.�ҵ�̹�˿��԰��������ӵ����ӵ�����������
 * 4.���˵�̹�˿��Է����ӵ����ӵ�������������ÿ0.5*n�뷢��һ���ӵ���
 * 5.������̹�˱��ӵ����е�ʱ�򣬲�����ըЧ��������ʧ��
 * 6.��ֹ����̹���ص��˶���
 * 7.���Էֹأ�
 * 8.������ͣ�ͼ�����
 * 9.���Լ�¼�ͱ��棻
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
	
	public Demo1_1()//���캯��
	{
		mp=new MyPanel(0);
		msp=new MyStartPanel();
		jm=new JMenu("�˵�(j)");
		jm.setMnemonic('j');
		jm.addActionListener(this);
		jm.setActionCommand("start");
		
		jmi=new JMenuItem("����Ϸ(n)");
		jmi.setMnemonic('n');
		jmi.addActionListener(this);
		jmi.setActionCommand("start");
		
		jmi3=new JMenuItem("������Ϸ(c)");
		jmi3.setMnemonic('c');
		jmi3.addActionListener(this);
		jmi3.setActionCommand("continue");
		
		jmi1=new JMenuItem("����(s)");
		jmi1.setMnemonic('s');
		jmi1.addActionListener(this);
		jmi1.setActionCommand("save");
		
		
		jmi2=new JMenuItem("�˳���Ϸ(e)");
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
		if(e.getActionCommand().equals("start"))//����Ϸ
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
		
		if(e.getActionCommand().equals("exit"))//�˳���Ϸ
		{
			//��������..
			Record.setEts(mp.ets);
			Record.keepRecord();
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("save"))//����
		{
			//����̹��λ�õ�
			Record.setEts(mp.ets);
			Record.keepRecord();
		}
		
		if(e.getActionCommand().equals("continue"))//������Ϸ
		{
			//������Ϸ
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


//��ʾ����
class MyStartPanel extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		Font f=new Font("�п�",Font.BOLD,20);
		g.setFont(f);
		g.setColor(Color.yellow);
		g.drawString("Gate1-1", 160, 130);
		
	}
}


class MyPanel extends JPanel implements KeyListener,Runnable
{
	static int pause=2;//��ͣ���
	static int tempetspeed=0;//��¼�з�̹���ٶ�ֵ
	static int tempsspeed=0;//��¼�ӵ����ٶ�ֵ
	MyTank mt=null;//�ҵ�̹�ˣ�
	Image image1=null;
	Image image2=null;
	Image image3=null;
	

	Vector<EnemyTank> ets=new Vector<EnemyTank>();//�з�̹�˵ļ���
	Vector<Boom> bs = new Vector<Boom>();//��ըЧ��
	
	
	int enSize=4;//�ط�̹������
	
	public MyPanel(int flag)
	{
			
		
		mt=new MyTank (250,200,2,1,0);//�ҵ�̹�˳�ʼ��
	//		Boom b=new Boom(mt.x+10,mt.y);//Ϊ���ڻ��е�һ��̹��֮ǰ��һ����ը��һ��̹����ʧ��ͬʱ�Ż��л���
	//		bs.add(b);
		if(flag==0)
		{
			for(int i=0;i<enSize;i++)//�з�̹�˳�ʼ��
			{
				EnemyTank et=new EnemyTank((i+1)*50,0,1,0,2);
				et.setEts(ets);//��panel�ϵĵ���̹�˵���������ǰ̹�ˣ�
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
				
				et.setEts(ets);//��panel�ϵĵ���̹�˵���������ǰ̹�ˣ�
				ets.add(et);			
				Thread t=new Thread(et);
				t.start();
				et.shotEnemy();
			}
		}
		//�����������ֱ�ӱ��������һ��̹��û�г��ֱ�ըЧ��������
		try {
			image1=ImageIO.read(new File("image4.png"));
			image2=ImageIO.read(new File("image2.png"));
			image3=ImageIO.read(new File("image3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		//��ըЧ����ʼ��(ͼ������)
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image4.png"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image2.png"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image3.png"));
	}
	public void paint(Graphics g)
	{

		super.paint(g);
		
		//��ʾ��Ϣ
		this.drawTank(40, 310, g, 0, 0);
		g.setColor(Color.black);
		g.drawString("x"+Record.getEnemyTankNum(), 65, 340);
		this.drawTank(90,310, g, 0, 1);
		g.setColor(Color.black);
		g.drawString("x"+Record.getMyTankLife(), 115, 340);
		
		//�ɼ�
		Font f = new Font("����",Font.BOLD,20);
		g.setFont(f);
		g.drawString("��ɱ̹����", 410, 40);
		this.drawTank(410, 50, g, 0, 0);
		g.setColor(Color.black);
		g.drawString("x"+Record.getDeadTankNum(), 435, 75);
		
		g.fillRect(0, 0, 400, 300);//ս����С
		
		// ���Լ���̹��
		if(mt.isLive==true){
			this.drawTank(mt.getX(), mt.getY(), g, mt.getDirect(), mt.getColor());
			}
		
		
		for(int i=0;i<ets.size();i++)//���з�̹��
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
		
		
		for(int i=0;i<mt.ss.size();i++)//���Լ����ӵ�
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
		
		
		//���з�̹���ӵ�
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

		for(int i=0;i<bs.size();i++)//���Ʊ�ը����
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
//		g.setFont(new Font("���Ĳ���",Font.BOLD,20));
//		g.drawString("�����˲�", 40, 40);
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
	
	
	//�ӵ��Ƿ����
	public void hitTank(shot s,Tank t)
	{
		switch(t.direct)
		{	
			case 0:
			case 2:
				if(s.x>=t.x&&s.x<=t.x+20&&s.y<=t.y+5&&s.y>=t.y+35)
				{
					//�ӵ�����
					s.isLive=false;
					//̹������
					t.isLive=false;
					//������ը
					Boom b=new Boom(t.x,t.y+5);
					bs.add(b);
					
				}
				//break;
			case 1:
			case 3:
				if(s.x>=t.x-5&&s.x<=t.x+25&&s.y>=t.y+5&&s.y<=t.y+25)
				{
					//�ӵ�����
					s.isLive=false;
					//̹������
					t.isLive=false;
					Boom b=new Boom(t.x-5,t.y+5);
					bs.add(b);
				}
				//break;
		}
	}
	
	//�ж��ӵ��Ƿ���ез�̹��
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
						//̹����������
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
	
	
	//�жϵз��ӵ��Ƿ�����Լ�
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
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)//��ͣ����
		{
			for(int i=0;i<this.ets.size();i++)
			{
				if(pause%2==0)
				{
					EnemyTank et=ets.get(i);
					et.setShotsize(0);
					//ȡ����̹�˵��ٶȣ�
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
			//�ж��ӵ��Ƿ���ез�̹��
			this.hitEnemyTank();
			//�ж��ӵ��Ƿ�����ҷ�̹��
			this.hitMyTank();

			
			this.repaint();
		}
	}
}


 