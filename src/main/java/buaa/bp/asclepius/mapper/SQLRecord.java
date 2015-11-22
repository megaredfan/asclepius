package buaa.bp.asclepius.mapper;

import java.util.List;

public interface SQLRecord {
	public int count();
	public List<?> selectByRange(int start,int length);
}
