package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Message;

public interface MessageMapper extends SQLRecord  {
	public List<Message> getAllRootMessages();
	public List<Message> getMessagesById(long pid);
	public int createMessage(Message message);
	public int updateMessage(Message message);
	public int deleteMessage(long id);
	
}
