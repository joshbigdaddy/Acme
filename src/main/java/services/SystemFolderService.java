package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import repositories.SystemFolderRepository;
import domain.Actor;
import domain.Message;
import domain.SystemFolder;

@Service
@Transactional
public class SystemFolderService {

	@Autowired
	private SystemFolderRepository systemFolderRepository;
	@Autowired
	private ActorRepository actorRepository;
	
//Supporting Services
	@Autowired
	private ActorService actorService;

//Constructor
	
	public SystemFolderService(){
		super();
	}

	
	public SystemFolder create(){
		Assert.notNull(systemFolderRepository);
		return new SystemFolder();
	}
	
	public Collection<SystemFolder> findAll(){
		return systemFolderRepository.findAll();
	}
	
	public SystemFolder findOne(Integer id){
		return systemFolderRepository.findOne(id);
	}
	
	public void save(SystemFolder i){
		Assert.notNull(systemFolderRepository);
		Assert.notNull(i);
		systemFolderRepository.save(i);
	}
	public void delete(SystemFolder i){
		Actor a=actorService.findByPrincipal();
		Assert.notNull(a);
		Assert.isTrue(a.getSystemFolder().contains(i));
		Assert.isTrue(i.getMessage().isEmpty());
		a.getSystemFolder().remove(i);
		actorRepository.save(a);
		systemFolderRepository.delete(i);
		
		
	}
	public Map<SystemFolder,Collection<Message>> displaySystemFolderWithMessage(){
		Map<SystemFolder,Collection<Message>> res=new HashMap<SystemFolder,Collection<Message>>();
		Collection<SystemFolder> c= findAll();
		for(SystemFolder s:c){
			res.put(s,s.getMessage());
		}
		return res;
	}
}
