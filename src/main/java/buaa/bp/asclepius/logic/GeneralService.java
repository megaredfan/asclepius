package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;

public  class GeneralService {
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	public  List<?> selectByRange(int start,int length) {
		return null;
	}
	public  int count() {
		return 0;
	}
	public List<?> generateList(HttpServletRequest request,HttpServletResponse response){
		String s_pageNo = "0";
		int pageNo = 0;
		int pageSize = configLoader.getInt("page.pageSize");
		int totalPages = (count() + pageSize - 1) / pageSize;
		s_pageNo = (String)request.getParameter("pageNo");
		try{
			pageNo = Integer.parseInt(s_pageNo);
		}catch(Exception e){
			
		}
		if(pageNo < 0){
			pageNo = 0;
		}
		if(pageNo > totalPages){
			pageNo = totalPages;
		}
		List<?> list = selectByRange(pageNo * pageSize,pageSize);
		return list;
	}
}
