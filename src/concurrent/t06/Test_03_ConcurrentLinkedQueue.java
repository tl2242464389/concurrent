package concurrent.t06;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并发容器 - ConcurrentLinkedQueue
 *  队列 - 底层单链表实现 
 */
public class Test_03_ConcurrentLinkedQueue {
	
	public static void main(String[] args) {
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		for(int i = 0; i < 10; i++){
			queue.offer("value" + i);
		}
		
		System.out.println(queue);
		System.out.println(queue.size());
		
		// peek() -> 查看queue中的队首数据
		System.out.println(queue.peek());
		System.out.println(queue.size());
		
		// poll() -> 移出并获取queue中的队首数据
		System.out.println(queue.poll());
		System.out.println(queue.size());
	}
}
