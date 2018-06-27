package my.study.test.deault;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;



public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Color.contains("GREEN1")?Color.valueOf("GREEN1"):"GREEN1");
		int i =  (int) Math.ceil((float)100/4);
		System.out.println(i);
		List<String> list =null;
		initList(list);
		System.out.println(list);
	}
	
	public static void initList(List<String> list){
		if( list ==null ){
			System.out.println("Îª¿ÕÁË");
			list = new ArrayList<String>();
		}
		list.add("3");
	}
	/*
	 * 
	 * 
new Timer().schedule(new TimerTask() {
	    public void run() {
	    	System.out.println(1);
	    	
		  }}, 1110);
	 * 
	 * 
	 * @Autowired
	private VehicleSMDao vehicleSMDao;
	@PostConstruct
	public void Test(){
		new Timer().schedule(new TimerTask() {
		    public void run() {
		    	try {
		    		System.out.println("new Timer().schedule(new TimerTask()");
		    		saveToStatisticsMap("vehicle_sm",vehicleSMDao.getVehicleSMByLimit(21,100));
		    		System.out.println("new Timer().schedule(new TimerTask()--");
		    		saveStatisticsToBD();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
			  }}, 10110);
	}*/
}
