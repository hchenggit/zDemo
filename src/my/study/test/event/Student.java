package my.study.test.event;

import java.util.Observable;
/**
 * �۲���
 * @author huangch
 * Since:JDK 7
 * Date:2018-7-13����3:58:02
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
        System.out.printf("ѧ��%s�۲쵽��ʵ���Ǳ�֪ͨ��%s��������ҵ��%s�� \n", this.name, teacher.getName(), arg);
    }

}
