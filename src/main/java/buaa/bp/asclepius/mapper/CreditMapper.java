package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Credit;

public interface CreditMapper extends SQLRecord  {
	public List<Credit> getAllCredits(long userId);
	public Credit getCreditById(long userId,long creditId);
	public int createCredit(Credit credit);
	public int updateCredit(Credit credit);
	public int deleteCredit(long id);
}
