package com.tankGame;

class Boom	//��ըЧ����
{
	int x,y;
	boolean isLive=true;
	int life=15;
	public Boom (int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void lifedown() //ÿ��ͼƬ������
	{
		if(life>0)
		{life--;}
		if(life<=0)
		{isLive=false;}
	}
	
	
	
	
}
