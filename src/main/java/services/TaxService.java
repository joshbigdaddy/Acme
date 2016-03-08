package services;


import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Item;
import domain.Tax;

import repositories.TaxRepository;

@Service
@Transactional
public class TaxService {

	@Autowired
	private TaxRepository taxRepository;
	
	
//Supporting Services

	@Autowired
	private ItemService itemService;
	//Constructor
	
	public TaxService(){
		super();
	}

	
	public Tax create(){
	
		return new Tax();
	}
	public Collection<Tax> findAll(){
		return taxRepository.findAll();
	}
	public Tax findOne(Integer id){
		return taxRepository.findOne(id);
	}
		
	public void save(Tax t){
	
		taxRepository.save(t);
	}
	public void delete(Tax t){
	
		Boolean y = true;
		
		for(Item item : itemService.findAll()){
			if(item.getTax().equals(t)){
				y=false;
			}
		}
		
		Assert.isTrue(y);
		taxRepository.delete(t);
		
	}
}
	