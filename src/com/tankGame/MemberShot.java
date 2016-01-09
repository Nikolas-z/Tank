package com.tankGame;

class shot implements Runnable
{
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

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	int x;
	int y;
	int direct;
	int speed;
	boolean isLive=true;
	public shot(int x,int y,int direct,int speed)
	{
		this.x=x;
		this.y=y; 
		this.direct=direct;
		this.speed=speed;
	}

	public void run() {
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct)
			{
			case 0://上
				y-=speed;
				break;
			case 1://右
				x+=speed;
				break;
			case 2://下
				y+=speed;
				break;
			case 3://左
				x-=speed;
				break;
			}
			//System.out.println(x+y);
			
			//死亡
			
			
			if(x<=0||x>=400||y<=0||y>=300)//是否碰界
			{
				this.isLive=false;
				break;
			}
			
		}
	}

}

