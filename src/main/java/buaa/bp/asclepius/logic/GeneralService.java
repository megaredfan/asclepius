package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.web.servlet.ModelAndView;

public  class GeneralService {
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	public  List<?> selectByRange(int start,int length) {
		return null;
	}
	public  int count() {
		return 0;
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
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
			pageNo = totalPages-1;
		}
		List<?> list = selectByRange(pageNo * pageSize,pageSize);
		m.addObject(listName,list);
		m.addObject("pageNo",pageNo);
		m.addObject("totalPages",totalPages);
	}
	
	public String generateListInJson(){
		return null;
	}
}
