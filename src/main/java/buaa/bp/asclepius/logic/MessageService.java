package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.MessageMapper;
import buaa.bp.asclepius.model.Message;

@Service
public class MessageService {
	@Resource(name="messageMapper")
	private MessageMapper messageMapper;
	
	public List<Message> getAllRootMessages(){
		return messageMapper.getAllRootMessages();
	}
	public List<Message> getMessagesById(long pid){
		return messageMapper.getMessagesById(pid);
	}
	public int createMessage(Message message){
		return messageMapper.createMessage(message);
	}
	public int updateMessage(Message message){
		return messageMapper.updateMessage(message);
	}
	public int deleteMessage(long id){
		return messageMapper.deleteMessage(id);
	}
	public int count() {
		return messageMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return messageMapper.selectByRange(start, length);
	}
}
