package com.test2;

public class Demo1_5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aaa a=new Aaa(10);
		Sss s=new Sss(10);
		Thread t1= new Thread(a);
		Thread t2=new Thread(s);
		t1.start();
		t2.start();
	}

	
}

class Aaa implements Runnable
{
	int n=0;
	int times=0;
	public Aaa(int n)
	{
		this.n=n;
	}
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("再输第"+times+"个 Hellow world");
			if (times==n)
			{
				break;
			}
		}
	}
}

class Sss implements Runnable
{
	int n=0;
	int res=0;
	int times=0;
	public Sss(int n)
	{
		this.n=n;
	}
	
	public void run()
	{
		while(true)
		{
			try{
				Thread.sleep(1000);
			}catch(Exception e)
			{
				
			} 
			res+=(++times);
			System.out.println("当前结果是"+times);
			if (times==n)
			{
				System.out.println("最后结果是"+res);
				break;
			}
		}
	}
	
}
