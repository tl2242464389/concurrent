package concurrent.t03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试锁
 */
public class Test_02 {
	Lock lock = new ReentrantLock();
	
	void m1(){
		try{
			lock.lock(); // 加锁
			for(int i = 0; i < 6; i++){
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1() method " + i);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	void m2(){
		boolean isLocked = false;
		try{
			// 尝试锁， 如果有锁，无法获取锁标记，返回false，如果无锁，则获取锁标记，返回true
			// 阻塞尝试锁，阻塞参数代表的时长，尝试指定时间内获取锁标记，如果超时，不等待。直接返回
			// isLocked = lock.tryLock(); 不考虑时长直接获取锁标记
			isLocked = lock.tryLock(3, TimeUnit.SECONDS); 
			if(isLocked){
				System.out.println("m1() unlock");
			}else{
				System.out.println("m1() lock");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(isLocked){
				// 尝试锁在解除锁标记的时候，一定要判断是否获取到锁标记。
				// 如果当前线程没有获取到锁标记，会抛出异常。
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		final Test_02 t = new Test_02();
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
