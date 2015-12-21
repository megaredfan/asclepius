package buaa.bp.asclepius.utils;

import java.sql.Date;
import java.util.Calendar;

public class TimeUtil {
	public static Date[] initDate(){
		Date start = null,end;
		Calendar calendar = Calendar.getInstance();

		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
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
		
		return new Date[]{start,end};
	}
}
