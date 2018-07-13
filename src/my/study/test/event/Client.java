package my.study.test.event;

import java.util.Observable;
import java.util.Observer;

public class Client {
	public static void main(String[] args) {
        Student student1= new Student("张三");
        Student student2 = new Student("李四");
        Teacher teacher1 = new Teacher("zuikc");
        teacher1.addObserver(student1);
        teacher1.addObserver(student2);
        teacher1.addObserver(new Observer(){
			public void update(Observable o, Object arg) {
				Teacher teacher = (Teacher) o;
				System.out.println("这不就是个监听吗！！！"+teacher.getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        });
        teacher1.setHomework("事件机制第一天作业");
    }
}
