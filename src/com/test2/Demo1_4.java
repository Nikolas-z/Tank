package com.test2;

public class Demo1_4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Say say=new Say();
		//�����߳�;
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
			//����1��
			//sleep�ĵ�λ��ms
			//sleep�����߳̽��뵽����Blocked״̬,���ͷ���Դ.
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
				//�˳�
				break;
			}
		}
			
	}
}