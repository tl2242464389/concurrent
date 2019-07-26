package concurrent.t01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicXxx
 * 同步类型,如AtomicInteger、AtomicLong、AtomicBoolean等等
 * 原子操作类型。 其中的每个方法都是原子操作。可以保证线程安全。底层实现依赖OS操作系统，效率较高
 */
public class Test_11 {
	
	// 原子类型的类
	AtomicInteger count = new AtomicInteger(0);
	
	void m(){
		for(int i = 0; i < 10000; i++){
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		final Test_11 t = new Test_11();
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					t.m();
				}
			}));
		}
		for(Thread thread : threads){
			thread.start();
		}
		for(Thread thread : threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(t.count.intValue());
	}
}
