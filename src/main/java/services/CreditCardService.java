package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.CreditCard;

import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {
		// Managed repository -----------------------------------------------------

		@Autowired
		private CreditCardRepository creditCardRepository;
		public CreditCardService(){
			super();
		}

		
		public CreditCard create(){
			CreditCard result;
			result= new CreditCard();
			return result;
		}
		public CreditCard findOne(int creditCardId) {
			CreditCard result;

			result = creditCardRepository.findOne(creditCardId);
		

			return result;
		}
		
		public List<CreditCard> findAll(){
			return creditCardRepository.findAll();
		}
			
		public Collection<CreditCard> showAll(){
			//Customer customer=customerService.findByPrincipal();
			//Assert.notNull(customer);
			return creditCardRepository.findAll();
		}
		public void save(CreditCard c){
			//Customer customer=administratorService.findByPrincipal();
			//Assert.notNull(customer);
			creditCardRepository.save(c);
		}
		public void delete(CreditCard c){
			//Customer customer=acustomerService.findByPrincipal();
			//Assert.notNull(customer);
			creditCardRepository.delete(c);
			
		}

}
