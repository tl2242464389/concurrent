package concurrent.t08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 无容量限制的线程池（最大容量默认为Integer.MAX_VALUE）
 */
public class Test_05_CachedThreadPool {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		
		for(int i = 0; i < 5; i++){
			service.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " - test executor");
				}
			});
		}
		
		System.out.println(service);
		
		try {
			TimeUnit.SECONDS.sleep(65);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//默认线程空闲 60 秒，自动销毁
		System.out.println(service);
	}
}
