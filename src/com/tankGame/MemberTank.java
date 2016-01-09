package com.tankGame;

import javax.swing.JPanel;
import java.util.*;

//̹����
class Tank extends JPanel
{
	Vector<shot> ss=new Vector();
	shot s=null;
	int x=0;
	int y=0;
	int direct=0;//0��ʾ��,1��ʾ��,2��ʾ��,3��ʾ��.
	int speed=1;
	int color;
	boolean isLive= true;
	int times=0;
	//����
	public 	void shotEnemy()
	{
		
		
		switch(this.direct)
		{
		case 0:
			s=new shot(x+10,y,0,3);
			//���ӵ����뵽����.
			ss.add(s);
			break;
		case 1:
			s=new shot(x+30,y+15,1,3);
			ss.add(s);
			break;
		case 2:
			s=new shot(x+10,y+40,2,3);
			ss.add(s);
			break;
		case 3:
			s=new shot(x-10,y+15,3,3);
			ss.add(s);
			break;
		}
		
		Thread t=new Thread(s);
		t.start();
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x,int y,int speed,int color,int direct)
	{
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.color=color;
		this.direct=direct;
	}
}

class EnemyTank extends Tank implements Runnable
{
	int shotsize=3;

	public int getShotsize() {
		return shotsize;
	}

	public void setShotsize(int shotsize) {
		this.shotsize = shotsize;
	}


	//����һ���������Է��ʵ�MyPanel�����е��˵�̹�ˣ�
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	
	
	public EnemyTank (int x,int y,int speed,int color,int direct)
	{
		super(x,y,speed,color,direct);
	}
	
	//�õ�MyPanel�ĵ���̹������
	public void setEts(Vector<EnemyTank> etss)
	{
		this.ets=etss;
	}
	
	//�ж��Ƿ������˱�ĵ���̹�ˣ�
	public boolean isTuochOtherEnemy()
	{
		boolean b=false;
		
		//��ʼ�ж�;
		switch(this.direct)
		{
		case 0:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
				if(et!=this)
				{
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+5<=et.y+5&&this.y+5>=et.y+35)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+5<=et.y+5&&this.y+5>=et.y+35)
						{
							return true;
						}
					}
					if(et.direct==1||et.direct==3)
					{
						if(this.x>=et.x-5&&this.x<=et.x+25&&this.y+5<=et.y+5&&this.y+5>=et.y+25)
						{
							return true;
						}
						if(this.x+20>=et.x-5&&this.x+20<=et.x+25&&this.y+5<=et.y+5&&this.y+5>=et.y+25)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
				if(et!=this)
				{
					if(et.direct==0||et.direct==2)
					{
						if(this.x+25>=et.x&&this.x+25<=et.x+20&&this.y+5>=et.y+5&&this.y+5<=et.y+35)
						{
							return true;
						}
						if(this.x+25>=et.x&&this.x+25<=et.x+20&&this.y+25>=et.y+5&&this.y+25<=et.y+35)
						{
							return true;
						}
					}
					if(et.direct==1||et.direct==3)
					{
						if(this.x+25>=et.x-5&&this.x+25<=et.x+25&&this.y+5>=et.y+5&&this.y+5<=et.y+25)
						{
							return true;
						}
						if(this.x+25>=et.x-5&&this.x+25<=et.x+25&&this.y+25>=et.y+5&&this.y+25<=et.y+25)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
				if(et!=this)
				{
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+35>=et.y+5&&this.y+35<=et.y+35)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+35>=et.y+5&&this.y+35<=et.y+35)
						{
							return true;
						}
					}
					if(et.direct==1||et.direct==3)
					{
						if(this.x>=et.x-5&&this.x<=et.x+25&&this.y+35>=et.y+5&&this.y+35<=et.y+25)
						{
							return true;
						}
						if(this.x+20>=et.x-5&&this.x+20<=et.x+25&&this.y+35>=et.y+5&&this.y+35<=et.y+25)
						{
							return true;
						}
					}
				}
			}
			
			break;
		case 3:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
				if(et!=this)
				{
					if(et.direct==0||et.direct==2)
					{
						if(this.x-5<=et.x+20&&this.x-5>=et.x&&this.y+5>=et.y+5&&this.y+5<=et.y+35)
						{
							return true;
						}
						if(this.x-5<=et.x+20&&this.x-5>=et.x&&this.y+25>=et.y+5&&this.y+25<=et.y+35)
						{
							return true;
						}
					}
					if(et.direct==1||et.direct==3)
					{
						if(this.x-5<=et.x+25&&this.x-5>=et.x-5&&this.y+5>=et.y+5&&this.y+5<=et.y+25)
						{
							return true;
						}
						if(this.x-5<=et.x+25&&this.x-5>=et.x-5&&this.y+25>=et.y+5&&this.y+25<=et.y+25)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		
		
		return b;
		
	}

	
	public void moveUp()
	{
		this.y-=speed;
	}
	public void moveDown()
	{
		this.y+=speed;
	}
	public void moveLeft()
	{
		this.x-=speed;
	}
	public void moveRight()
	{
		this.x+=speed;
	}


	@Override
	public void run() {
		
		while(true)
		{
			
				if(shotsize!=0)
				{
					this.direct=(int)(Math.random()*4);
				}
				int n=(int)(Math.random()*30)+20;
					for(int i=0;i<n;i++){
					
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	
						switch(this.direct)
							{
								case 0:
									if(y-5>0&&!this.isTuochOtherEnemy()){
									y-=speed;}
									else{
										i=n-1;
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									break;
								case 1:
									if(x+35<400&&!this.isTuochOtherEnemy()){
									x+=speed;}
									else{
										i=n-1;
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									break;
								case 2:
									if(y+40<300&&!this.isTuochOtherEnemy()){
									y+=speed;}
									else{
										i=n-1;
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									break;
									
								case 3:
									if(x-10>0&&!this.isTuochOtherEnemy()){
									x-=speed;}
									else{
										i=n-1;
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									break;
									
							}
						
						
					}
					
	
				if(this.isLive==false)
				{	
					break;
				}
				times++;
				//���˷����ӵ�
				if(times%1==0)
				{
					if(this.isLive==true)
					{
						if(this.ss.size()<shotsize)//û���ӵ�
						{ this.shotEnemy();}
					}
				}
				
				
			
			
		}
	}
}
class MyTank extends Tank
{
	public MyTank (int x ,int y,int speed,int color,int direct)
	{
		super(x,y,speed,color,direct);
	}
	public void moveUp()
	{
		this.y-=speed;
	}
	public void moveDown()
	{
		this.y+=speed;
	}
	public void moveLeft()
	{
		this.x-=speed;
	}
	public void moveRight()
	{
		this.x+=speed;
	}

}