package buaa.bp.asclepius.servlet;

import java.util.Calendar;
import java.sql.Date;

import org.junit.Test;

public class GeneralServletTest {
	@Test
	public void test(){
		Calendar calendar = Calendar.getInstance();
//		Date date = Date.valueOf("2015-11-21");
//		calendar.setTime(new java.util.Date(date.getTime()));
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(day);
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH)+1);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		
		Date start=null,end;

		switch(day){
		case 1://周日
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			start = new Date(calendar.getTime().getTime());
			break;
		case 2://周一
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			start = new Date(calendar.getTime().getTime());
			break;
		case 3://周二
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			start = new Date(calendar.getTime().getTime());
			break;
		case 4://周三
			calendar.add(Calendar.DAY_OF_MONTH, 5);
			start = new Date(calendar.getTime().getTime());
			break;
		case 5://周四
			calendar.add(Calendar.DAY_OF_MONTH, 4);
			start = new Date(calendar.getTime().getTime());
			break;
		case 6://周五
			calendar.add(Calendar.DAY_OF_MONTH, 3);
			start = new Date(calendar.getTime().getTime());
			break;
		case 7://周六
			calendar.add(Calendar.DAY_OF_MONTH, 2);
			start = new Date(calendar.getTime().getTime());
			break;
		default:
			break;
		}
		calendar.setTime(start);
		calendar.add(Calendar.DAY_OF_MONTH, 4);
		end = new Date(calendar.getTime().getTime());
		
		System.out.println(start + " to " + end );
	}
	
}
