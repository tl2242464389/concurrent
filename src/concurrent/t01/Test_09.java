package concurrent.t01;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字
 * volatile:可见性
 * 作用：通知OS操作系统底层，在CPU计算过程中，都要检查内存中数据的有效性。保证最新的内存数据被使用。
 * CPU的一级缓存、二级缓存、三级缓存的作用都是存放临时数据
 */
public class Test_09 {
	
	volatile boolean b = true;  //启用volatile关键字能够将堆内存中b的数据与CPU缓存中b的数据实时同步
	
	void m(){
		System.out.println("start");
		while(b){
		}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		final Test_09 t = new Test_09();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.b = false;
	}
}
