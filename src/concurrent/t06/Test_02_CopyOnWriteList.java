package concurrent.t06;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 并发容器 - CopyOnWriteList
 * 写入效率低，读取效率高。每次写入数据，都会创建一个新的底层数组，复制集合，当移出最后一个元素时效率较高，底层实现直接返回上一个数组
 * 复制集合后并不会立马回收上一个数组对象，通过占用空间减少查询时间
 * 适用场景：写入频率低，读取频率高的情况
 */
public class Test_02_CopyOnWriteList {
	
	public static void main(String[] args) {
		// final List<String> list = new ArrayList<>();
		// final List<String> list = new Vector<>();
		final List<String> list = new CopyOnWriteArrayList<>();
		final Random r = new Random();
		Thread[] array = new Thread[100];
		final CountDownLatch latch = new CountDownLatch(array.length);
		
		long begin = System.currentTimeMillis();
		for(int i = 0; i < array.length; i++){
			array[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j = 0; j < 1000; j++){
						list.add("value" + r.nextInt(100000));
					}
					latch.countDown();
				}
			});
		}
		for(Thread t : array){
			t.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("执行时间为 ： " + (end-begin) + "毫秒！");
		System.out.println("List.size() : " + list.size());
	}
}
