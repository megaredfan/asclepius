package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.CreditMapper;
import buaa.bp.asclepius.model.Credit;

@Service
public class CreditService extends GeneralService  {
	
	@Resource(name="creditMapper")
	private CreditMapper creditMapper;
	
	public List<Credit> getAllCredits(long userId) {
		return creditMapper.getAllCredits(userId);
	}
	public Credit getCreditById(long userId,long creditId) {
		return creditMapper.getCreditById(userId, creditId);
	}
	public int createCredit(Credit credit) {
		return creditMapper.createCredit(credit);
	}
	public int updateCredit(Credit credit) {
		return creditMapper.updateCredit(credit);
	}
	public int deleteCredit(long id) {
		return creditMapper.deleteCredit(id);
	}
	public int count() {
		return creditMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return creditMapper.selectByRange(start, length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
}
