package com.test2;

public class Demo1_4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Say say=new Say();
		//启动线程;
		Thread t=new Thread(say);
		t.start();
		
	}

}

class Say implements Runnable
{
	int i=0;
	public void run()
	{
		while(true)
		{
			//休眠1秒
			//sleep的单位是ms
			//sleep会让线程进入到阻塞Blocked状态,并释放资源.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			System.out .println("Hellow worid"+i);
			if(i==10)
			{
				//退出
				break;
			}
		}
			
	}
}