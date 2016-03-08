package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoresRepository;
import domain.Stores;


@Service
@Transactional
public class StoresService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private StoresRepository storesRepository;	
	
	// Constructors -----------------------------------------------------------

	public StoresService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	public Stores create() {
		Stores result;

		result = new Stores();

		return result;
	}
	public Collection<Stores> findAll() {
		Collection<Stores> result;
		
		result = storesRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	public Stores findOne(int StoresId) {
		Stores result;

		result = storesRepository.findOne(StoresId);

		return result;
	}

	
	public void save(Stores store) {
		Assert.notNull(store);

		storesRepository.save(store);
	}	
	public void delete(Stores store) {
		Assert.notNull(store);

		storesRepository.delete(store);
	}	
}
