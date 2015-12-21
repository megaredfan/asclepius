package buaa.bp.asclepius.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.MessageMapper;
import buaa.bp.asclepius.model.Message;

@Service
public class MessageService extends GeneralService {
	@Resource(name="messageMapper")
	private MessageMapper messageMapper;
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	@Cacheable(value="message")
	public List<Message> getAllRootMessages(){
		return messageMapper.getAllRootMessages();
	}
	@Cacheable(value="message")
	public List<Message> getMessagesByPid(long pid){
		return messageMapper.getMessagesByPid(pid);
	}
	@Cacheable(value="message")
	public List<Message> getUnReplyedMessages(){
		return messageMapper.getUnReplyedMessages();
	}
	@CacheEvict(value="message")
	public int createMessage(Message message){
		return messageMapper.createMessage(message);
	}
	@CacheEvict(value="message")
	public int updateMessage(Message message){
		return messageMapper.updateMessage(message);
	}
	@CacheEvict(value="message")
	public int deleteMessage(long id){
		return messageMapper.deleteMessage(id);
	}
	@Cacheable(value="message")
	public Message getMessageById(long id){
		return messageMapper.getMessageById(id);
	}
	public int count() {
		return messageMapper.count();
	}
	@Cacheable(value="message")
	public List<?> selectByRange(int start,int length) {
		return messageMapper.selectByRange(start, length);
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
		List<Message> clist = new ArrayList<Message>();
		for(Object obj : list)
		{
			Message msg = (Message)obj;
			for(Message cmsg : messageMapper.getMessagesByPid(msg.getId()))
			{
				clist.add(cmsg);
			}
		}
		m.addObject(listName,list);
		m.addObject("childMessages",clist);
		m.addObject("pageNo",pageNo);
		m.addObject("totalPages",totalPages);
	}
}
