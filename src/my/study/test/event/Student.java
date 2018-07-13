package my.study.test.event;

import java.util.Observable;
/**
 * 观察者
 * @author huangch
 * Since:JDK 7
 * Date:2018-7-13下午3:58:02
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class Student implements java.util.Observer{

	private String name;
    public Student(String name){
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        Teacher teacher = (Teacher) o;
        System.out.printf("学生%s观察到（实际是被通知）%s布置了作业《%s》 \n", this.name, teacher.getName(), arg);
    }

}
