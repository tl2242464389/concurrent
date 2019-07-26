package concurrent.t03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * ReentrantLock可以定义公平锁，线程间公平调用
 * synchronized是不公平的，线程间互相竞争同一把锁
 */
public class Test_04 {
	
	public static void main(String[] args) {
		//TestReentrantlock t = new TestReentrantlock();
		TestSync t = new TestSync();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
	}
}

class TestReentrantlock extends Thread{
	// 定义一个公平锁
	private static ReentrantLock lock = new ReentrantLock(true);
	
	public void run(){
		for(int i = 0; i < 5; i++){
			lock.lock();
			try{
				System.out.println(Thread.currentThread().getName() + " get lock ");
			}finally{
				lock.unlock();
			}
		}
	}
}

class TestSync extends Thread{
	public void run(){
		for(int i = 0; i < 5; i++){
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + " get lock in TestSync");
			}
		}
	}
}
