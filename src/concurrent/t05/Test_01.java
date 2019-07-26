package concurrent.t05;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * 就是一个Map。key --> Thread.getCurrentThread().  value --> 线程需要保存的变量
 * ThreadLocal.set(value) --> map.put(Thread.getCurrentThread(), value);
 * ThreadLocal.get() --> map.get(Thread.getCurrentThread());
 * 内存溢出：在并发量高的时候，存储的数据量过大导致内存溢出
 * 内存泄漏：在系统比较繁忙时，一个线程执行结束，可能只会清空该线程的栈信息，本身ID不会改变，那么在获得变量时就会获取到上次执行保存的变量
 * 使用ThreadLocal的时候，一定注意回收资源问题，每个线程结束之前，将当前线程保存的线程变量一定要删除 ，防止内存溢出和内存泄漏
 * ThreadLocal.remove();
 */
public class Test_01 {

	volatile static String name = "zhangsan";
	static ThreadLocal<String> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name);
				System.out.println(tl.get());
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				name = "lisi";
				tl.set("wangwu");
			}
		}).start();
	}
}
