package com.tankGame;

import java.io.*;
import java.util.*;

//��������
class Record 
{  
	private static int EnemyTankNum = 20;//��������
	private static int MyTankLife = 3;//�Լ�����
	private static int DeadTankNum = 0;//�������ĵ��˵�̹������
			
	private static FileWriter fw=null;
	private static BufferedWriter bw = null;
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private static Vector<EnemyTank> ets= new Vector<EnemyTank>();//ȡ��MyPanel�����̹��
	
	//ȡ�����˵�̹������
	public  static Vector<EnemyTank> getRecord()
	{
		try {
			fr=new FileReader("d:\\java�ļ�\\tanklesson\\myrecord.txt");
			br=new BufferedReader(fr);
			
			String n="";
			//��ȥ����һ��
			n=br.readLine();
			DeadTankNum=Integer.parseInt(n);
			while((n=br.readLine())!=null)
			{
				String []p =n.split(" ");
				EnemyTank et=new EnemyTank(Integer.parseInt(p[0]),Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4]));
				ets.add(et);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		 return ets;
		
	}
	
	//����̹�˵�����
	public static void keepRecord()
	{
		try {
			fw=new FileWriter("d:\\java�ļ�\\tanklesson\\myrecord.txt");
			bw=new BufferedWriter(fw);
			
			
			bw.write(DeadTankNum+"\r\n");//�ݻ�����
			//�������λ��
			for(int i=0;i<ets.size();i++)
				{
					EnemyTank et=ets.get(i);
					if(et.isLive)
					{
						String n=et.x+" "+et.y+" "+et.speed+" "+et.color+" "+et.direct;						
						bw.write(n+"\r\n");
						
					}
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	//ȡ����¼��
//	public static void getDTN()
//	{
//		try {
//			fr=new FileReader("d:\\java�ļ�\\tanklesson\\myrecord.txt");
//			br=new BufferedReader(fr);
//			String s=br.readLine();
//			DeadTankNum=Integer.parseInt(s);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally
//		{
//			
//			try {
//				br.close();
//				fr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	//��¼̹�˱��ݻٵ�����
//	public static void keepDTN()
//	{
//		try {
//			fw=new FileWriter("d:\\java�ļ�\\tanklesson\\myrecord.txt");
//			bw=new BufferedWriter(fw);
//			
//			
//			bw.write(DeadTankNum+"\r\n");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally
//		{
//			try {
//				bw.close();
//				fw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		
//	}
	
	
	
	
	
	
	
	
	public static int getDeadTankNum() {
		return DeadTankNum;
	}
	public static void setDeadTankNum(int deadTankNum) {
		DeadTankNum = deadTankNum;
	}
	public static int getEnemyTankNum() {
		return EnemyTankNum;
	}
	public static void setEnemyTankNum(int enemyTankNum) {
		EnemyTankNum = enemyTankNum;
	}
	public static int getMyTankLife() {
		return MyTankLife;
	}
	public static void setMyTankLife(int myTankLife) {
		MyTankLife = myTankLife;
	}
	public static void ReduceETN()
	{
		EnemyTankNum--;
	}
	public static void ReduceMTL()
	{
		MyTankLife--;
	}
	public static void AddDTN()
	{
		DeadTankNum++;
	}
	
	//�õ�MyPanel�ĵ���̹������
	public static void setEts(Vector<EnemyTank> etss)
	{
		ets=etss;
	}
	
}
