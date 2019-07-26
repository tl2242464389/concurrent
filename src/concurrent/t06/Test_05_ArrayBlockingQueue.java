package concurrent.t06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * 有界容器 - ArrayBlockingQueue
 * 底层数组实现的有界队列，当容量不足的时候，自动阻塞。根据调用 API（add/put/offer）不同，有不同特性。
 * 	add 方法在容量不足的时候，抛出异常。
 * 	put 方法在容量不足的时候，阻塞等待。
 * 	offer 方法，
 * 		单参数 offer 方法，不阻塞。容量不足的时候，返回 false。当前新增数据操作放弃。
 * 		三参数 offer 方法（offer(value,times,timeunit)），容量不足的时候，阻塞 times 时长（单位为 timeunit），
 * 		如果在阻塞时长内，有容量空闲，新增数据返回 true。如果阻塞时长范围内，无容量空闲，放弃新增数据，返回 false
 */
public class Test_05_ArrayBlockingQueue {
	
	final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
	
	public static void main(String[] args) {
		final Test_05_ArrayBlockingQueue t = new Test_05_ArrayBlockingQueue();
		
		for(int i = 0; i < 5; i++){
			//add()方法
			// System.out.println("add method : " + t.queue.add("value"+i));
			
			//put()方法
			/*try {
				t.queue.put("put"+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("put method : " + i);*/
			
			//单参数offer()方法
			// System.out.println("offer method : " + t.queue.offer("value"+i));  
			
			//三参数offer()方法
			try {
				System.out.println("offer method : " + 
							t.queue.offer("value"+i, 1, TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(t.queue);
	}
}
