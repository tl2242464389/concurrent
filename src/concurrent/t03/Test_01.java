package concurrent.t03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 重入锁
 * 加锁与解锁应用
 */
public class Test_01 {
	Lock lock = new ReentrantLock();
	
	void m1(){
		try{
			lock.lock(); // 加锁
			for(int i = 0; i < 5; i++){
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1() method " + i);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock(); // 解锁
		}
	}
	
	void m2(){
		lock.lock(); // 加锁，如果对象有锁，则进入阻塞状态进行等待
		System.out.println("m2() method");
		lock.unlock(); // 解锁
	}
	
	public static void main(String[] args) {
		final Test_01 t = new Test_01();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();
	}
}
