package services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomizationRepository;
import domain.Customization;

@Service
@Transactional
public class CustomizationService {
	@Autowired
	private CustomizationRepository customizationRepository;
	
	
//Supporting Services

	
	//Constructor
	
	public CustomizationService(){
		super();
	}

	
	public Customization create(){
		Customization result;

		result = new Customization();		

		return result;
	}
	public Customization findOne(int customizationId) {
		Customization result;

		result = customizationRepository.findById(customizationId);

		return result;
	}
	
	public List<Customization> findAll() {
		List<Customization> result;

		result = customizationRepository.findAll();

		return result;
	}
	public void save(Customization c){
		Assert.notNull(c);
		customizationRepository.save(c);
	}
}
