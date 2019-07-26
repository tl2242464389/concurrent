package concurrent.t01;

/**
 * synchronized关键字
 * 常量问题
 * 在定义同步代码块时，不要使用常量对象作为锁对象。
 */
public class Test_14 {
	String s1 = "hello";
	String s2 = new String("hello"); // new关键字，一定是在堆中创建一个新的对象。
	String s3 = "hello"; // 常量池中的对象都是一个对象，"hello" = "he" + "llo"，常量池的特殊处理，提升效率
	
	void m1(){
		synchronized (s1) {
			System.out.println("m1()");
			while(true){
			}
		}
	}
	
	void m2(){
		synchronized (s2) {
			System.out.println("m2()");
			while(true){
			}
		}
	}
	
	void m3(){
		synchronized (s3) {
			System.out.println("m3()");
			while(true){
			}
		}
	}
	
	public static void main(String[] args) {
		final Test_14 t = new Test_14();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m3();
			}
		}).start();
	}
}
