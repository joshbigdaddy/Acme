package security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;	
	
	// Supporting services ----------------------------------------------------
		
	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}
	
	public UserAccount create() {
		UserAccount result;

		result = new UserAccount();		

		return result;
	}
	// Simple CRUD methods ----------------------------------------------------
	
	public UserAccount findByUsername(Actor actor) {
		Assert.notNull(actor);
		
		UserAccount result;
		
		result = userAccountRepository.findByUsername(actor.getUserAccount().getUsername());		
		
		return result;
	}
	public UserAccount findOne(int userAccountId) {
		UserAccount result;
		
		result = userAccountRepository.findById(userAccountId);		

		return result;
	}

	public void save(UserAccount userAccount) {
		userAccountRepository.save(userAccount);
	}

	public List<UserAccount> findAll() {
		
		return userAccountRepository.findAll();
	}
	
	// Other business methods -------------------------------------------------

}