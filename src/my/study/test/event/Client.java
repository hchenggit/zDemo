package my.study.test.event;

import java.util.Observable;
import java.util.Observer;
/**
 * https://www.cnblogs.com/luminji/p/6944526.html
 * @author huangch
 * Since:JDK 7
 * Date:2018-7-13����4:17:16
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class Client {
	public static void main(String[] args) {
        Student student1= new Student("����");
        Student student2 = new Student("����");
        Teacher teacher1 = new Teacher("zuikc");
        teacher1.addObserver(student1);
        teacher1.addObserver(student2);
        teacher1.addObserver(new Observer(){
			public void update(Observable o, Object arg) {
				Teacher teacher = (Teacher) o;
				System.out.println("�ⲻ���Ǹ������𣡣���"+teacher.getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        });
        teacher1.setHomework("�¼����Ƶ�һ����ҵ");
    }
}
