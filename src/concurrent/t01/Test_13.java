package concurrent.t01;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * 锁对象变更问题
 * 同步代码一旦加锁后，那么会有一个临时的锁引用执行锁对象，和真实的引用无直接关联，临时的锁引用信息存放在栈帧
 * 在锁未释放之前，修改锁对象引用，不会影响同步代码的执行，此时同步方法锁定的obj对象还是之前的对象，而打印的obj地址是new之后的新对象地址
 */
public class Test_13 {
	Object obj = new Object();
	
	void m(){
		System.out.println(Thread.currentThread().getName() + " start");
		synchronized (obj) {
			while(true){
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " - " + obj);
			}
		}
	}
	
	public static void main(String[] args) {
		final Test_13 t = new Test_13();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}, "thread1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}, "thread2");
		
		t.obj = new Object();
		thread2.start();
	}
}
