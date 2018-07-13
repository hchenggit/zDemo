package my.study.test.event;

import java.util.ArrayList;
import java.util.List;
/**
 * ���۲���
 * @author huangch
 * Since:JDK 7
 * Date:2018-7-13����3:58:12
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class Teacher extends java.util.Observable{
	 private String name;
	    private List<String> homeworks;

	    public String getName() {
	        return this.name;
	    }

	    public Teacher(String name) {
	        this.name = name;
	        homeworks = new ArrayList<String>();
	    }

	    public void setHomework(String homework) {
	        System.out.printf("%s��������ҵ%s \n", this.name, homework);
	        homeworks.add(homework);
	        setChanged();
	        notifyObservers(homework);

	    }
}
