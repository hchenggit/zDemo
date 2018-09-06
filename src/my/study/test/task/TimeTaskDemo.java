package my.study.test.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
////alert("<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance(Locale.CHINA).getTime())%>")

public class TimeTaskDemo {
	public static void main(String[] args) throws ParseException {
		System.out.println("��ʱ������ʱ�䣺" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tomorrowSmallHours()) );
		/*new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("��ʼִ��: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}}, getSmallHours(),5 * 60 * 1000);//24 * 60
		*/
		getCur();
	}
	
	public static Date getSmallHours() throws ParseException{
		String yyyymmdd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//�����賿
		Date smallHours = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yyyymmdd+" 09:45:00");
		Calendar ca=Calendar.getInstance();
		ca.setTime(smallHours);
		//����ڱ���ʱ�䣬ȡ����ʱ����賿
		//ca.add(Calendar.HOUR_OF_DAY, -8);
		return ca.getTime();
	}
	
	public static Date tomorrowSmallHours() throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		//�����賿
		Calendar ca=Calendar.getInstance();
		ca.setTime(sf.parse(sf.format(new Date())));
		//�����賿
		ca.add(Calendar.HOUR_OF_DAY, 24);
		return ca.getTime();
	}
	public static void getCur(){
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        System.out.println(dateString);
        
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance(Locale.CHINA).getTime());
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		//�����賿
		Calendar ca=Calendar.getInstance();
		try {
			ca.setTime(sf.parse(sf.format(new Date())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����賿
		ca.add(Calendar.HOUR_OF_DAY, 24-8);
		
        ;
	}
}
