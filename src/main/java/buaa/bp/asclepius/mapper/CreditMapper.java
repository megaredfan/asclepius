package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Credit;

public interface CreditMapper {
	public List<Credit> getAllCredits(int userId);
	public Credit getCreditById(int userID,int creditId);
	public int createCredit(Credit credit);
	public int updateCredit(Credit credit);
	public int deleteCredit(int id);
}
