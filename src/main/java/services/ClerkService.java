package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ClerkRepository;
import repositories.OrderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Clerk;
import domain.Order;

@Service
@Transactional
public class ClerkService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ClerkRepository clerkRepository;
	@Autowired
	private OrderRepository orderRepository;	
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ClerkService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

	public Clerk create() {
		Clerk result;

		result = new Clerk();

		return result;
	}

	public Collection<Clerk> findAll() {
		Collection<Clerk> result;

		result = clerkRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Clerk findOne(int clerkId) {
		Clerk result;

		result = clerkRepository.findOne(clerkId);

		return result;
	}

	public void save(Clerk clerk) {
		Assert.notNull(clerk);

		clerkRepository.save(clerk);
	}

	public void delete(Clerk clerk) {
		Assert.notNull(clerk);
		Assert.isTrue(clerk.getId() != 0);
		Assert.isTrue(clerk.getOrders().isEmpty());

		clerkRepository.delete(clerk);
	}

	// Other business methods -------------------------------------------------
	public Clerk findByPrincipal() {
		Clerk result;
		UserAccount userAccount;
	
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	
	public Clerk findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Clerk result;

		result = clerkRepository.findByUserAccountId(userAccount.getId());		

		return result;
	}
	public Collection<Clerk> clerkMoreDeliveredOrders(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Clerk> result;
		result= clerkRepository.clerkWithMoreDeliveredOrders();
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Clerk> clerkLessDeliveredOrders(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Clerk> result;
		result= clerkRepository.clerkWithLessDeliveredOrders();
		Assert.notNull(result);
		
		return result;
	}
	public void assignOrder(Order order){
		Clerk clerk=findByPrincipal();
		Assert.notNull(clerk);
		Collection<Clerk> clerks=findAll();
		boolean res=true;
		for(Clerk cl:clerks){
			if(cl.getOrders().contains(order)){
				res=false;
				break;
			}
		}
		Assert.isTrue(res);
		order.setClerk(clerk);
		clerk.getOrders().add(order);
		clerkRepository.save(clerk);
		orderRepository.save(order);
		
	}
	public void markAsDelivered(Order order){
		Clerk clerk=findByPrincipal();
		Assert.notNull(clerk);
		Assert.isTrue(clerk.getOrders().contains(order));
		order.setDeliverMoment(new Date(System.currentTimeMillis()-1));
		orderRepository.save(order);
	}

	public boolean AssertClerkEqual(Order order){
		boolean aux=true;
		if(order.getClerk()==null){
			aux=false;
		}else{
			if(order.getClerk().getId()!=findByPrincipal().getId()){
				aux=false;
			}
		}
		return aux;
		}
	


}
