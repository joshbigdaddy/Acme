package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;
import domain.SystemFolder;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	
//Supporting Services

//Constructor
	
	public MessageService(){
		super();
	}

	
	public Message create(){
		Assert.notNull(messageRepository);
		return new Message();
	}
	
	public Collection<Message> findAll(){
		return messageRepository.findAll();
	}
	
	
	
	public void save(Message i){
		Assert.notNull(messageRepository);
		Assert.notNull(i);
		messageRepository.save(i);
	}
	public void delete(Message i){
		Assert.notNull(i);
		SystemFolder sistem=i.getSystemFolder();
		sistem.getMessage().remove(i);
		messageRepository.delete(i);
		
		
		
		
	}


	public Message findOne(Integer messageId) {
		// TODO Auto-generated method stub
		return messageRepository.findOne(messageId);
	}
}
