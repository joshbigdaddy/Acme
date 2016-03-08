package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	public Administrator create() {
		Administrator result;

		result = new Administrator();		

		return result;
	}
	
	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		Assert.notNull(administratorRepository);
		result = administratorRepository.findAll();		
		Assert.notNull(result);
		
		return result;
	}

	public Administrator findOne(int administratorId) {
		Administrator result;
		
		result = administratorRepository.findOne(administratorId);		

		return result;
	}
	
	public void save(Administrator administrator) {
		Assert.notNull(administrator);
		administratorRepository.save(administrator);
	}	
	public void delete(Administrator admin) {
		Assert.notNull(admin);
		Assert.isTrue(admin.getId() != 0);
		Assert.isTrue(administratorRepository.exists(admin.getId()));
		administratorRepository.delete(admin);
		
	}

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
		}
		public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount.getId()); 

		return result;
		}
}
