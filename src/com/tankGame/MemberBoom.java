package com.tankGame;

class Boom	//爆炸效果类
{
	int x,y;
	boolean isLive=true;
	int life=15;
	public Boom (int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void lifedown() //每张图片的生命
	{
		if(life>0)
		{life--;}
		if(life<=0)
		{isLive=false;}
	}
	
	
	
	
}
