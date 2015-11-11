package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.CreditMapper;
import buaa.bp.asclepius.model.Credit;

@Service
public class CreditService {
	
	@Resource(name="creditMapper")
	private CreditMapper creditMapper;
	
	public List<Credit> getAllCredits(int userId) {
		return creditMapper.getAllCredits(userId);
	}
	public Credit getCreditById(int userID,int creditId) {
		return creditMapper.getCreditById(userID, creditId);
	}
	public int createCredit(Credit credit) {
		return creditMapper.createCredit(credit);
	}
	public int updateCredit(Credit credit) {
		return creditMapper.updateCredit(credit);
	}
	public int deleteCredit(int id) {
		return creditMapper.deleteCredit(id);
	}
}
