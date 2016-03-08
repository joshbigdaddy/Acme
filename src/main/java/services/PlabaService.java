package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Consumer;
import domain.Plaba;

import repositories.PlabaRepository;

@Service
@Transactional
public class PlabaService {

	@Autowired
	private PlabaRepository plabaRepository;
	@Autowired
	private ConsumerService consumerService;
	
//Supporting Services

	
	//Constructor
	
	public PlabaService(){
		super();
	}

	
	public Plaba create(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Plaba result;
		result= new Plaba();
		return result;
	}
	public Plaba findOne(int plabaId) {
		Plaba result;

		result = plabaRepository.findOne(plabaId);
	

		return result;
	}
	
	public Collection<Plaba> findAll(){
		return plabaRepository.findAll();
	}
		
	public Collection<Plaba> showAll(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		return plabaRepository.findAll();
	}
	public void save(Plaba c){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
	   plabaRepository.save(c);
		
	}
	public void delete(Plaba c){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		plabaRepository.delete(c);
		
	}


	public void assignConsumer(Plaba plaba,Consumer consumer) {
		// TODO Auto-generated method stub
		consumer=consumerService.findOne(consumer.getId());
		plaba.setConsumer(consumer);
		plabaRepository.save(plaba);
	}
	
}