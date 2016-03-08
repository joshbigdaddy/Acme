package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConsumerRepository;
import repositories.ShoppingCartRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Comment;
import domain.Consumer;
import domain.Order;
import domain.ShoppingCart;
import domain.ShoppingCartComment;
import domain.SystemFolder;

@Service
@Transactional
public class ConsumerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ConsumerRepository consumerRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private CustomizationService customizationService;
	@Autowired
	private UserAccountService userAccountService;
	
	// Constructors -----------------------------------------------------------

	public ConsumerService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

	public Consumer create() {
		Consumer result;

		result = new Consumer();

		return result;
	}

	public Collection<Consumer> findAll() {
		Collection<Consumer> result;

		result = consumerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Consumer findOne(int ConsumerId) {
		Consumer result;

		result = consumerRepository.findById(ConsumerId);

		return result;
	}

	public void save(Consumer consumer) {
		Assert.notNull(consumer);

		consumerRepository.save(consumer);
	}

	public void delete(Consumer consumer) {
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		Assert.isTrue(consumer.getOrder().isEmpty());
		Assert.isTrue(consumer.getComment().isEmpty());

		consumerRepository.delete(consumer);
	}

	// Other business methods -------------------------------------------------
	public Consumer findByPrincipal() {
		Consumer result;
		UserAccount userAccount;
	
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	
	public Consumer findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Consumer result;

		result = consumerRepository.findByUserAccountId(userAccount.getId());		

		return result;
	}
	
	public Collection<Consumer> findConsumerMoreOrders(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Consumer> result;
		
		result=consumerRepository.findConsumerWhoPlacedMoreOrders();
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Consumer> findConsumerMoreMoney(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Consumer> result;
		
		result=consumerRepository.findConsumerWhoSpentMoreMoney();
		Assert.notNull(result);
		
		return result;
		
	}
	public Collection<Consumer> consumerMoreCancelledOrders(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Consumer> result;
		
		result=consumerRepository.consumerWithMoreCancelledOrders();
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Consumer> consumerLessCancelledOrders(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Consumer> result;
		
		result=consumerRepository.consumerWithLessCancelledOrders();
		Assert.notNull(result);
		
		return result;
	}
	public ShoppingCart showShoppingcart(){
		Consumer consumer;
		consumer= findByPrincipal();
		Assert.notNull(consumer);
		ShoppingCart shopcart=consumer.getShoppingCart();
		Assert.notNull(shopcart);
		
		
		return shopcart;
	}
	public void createCommment(ShoppingCartComment comment){
	Consumer consumer=findByPrincipal();
	Assert.notNull(consumer);
	ShoppingCart shopc=consumer.getShoppingCart();
	Assert.notNull(shopc);
	shopc.getComment().add(comment);
	shoppingCartRepository.save(shopc);
	}
	public void deleteComment(String comment){
		Consumer consumer=findByPrincipal();
		Assert.notNull(consumer);
		ShoppingCart shopc=consumer.getShoppingCart();
		Assert.notNull(shopc);
		shopc.getComment().remove(comment);
		shoppingCartRepository.save(shopc);
	}
	public void modifyComment(ShoppingCartComment comment,ShoppingCartComment previous){
		Consumer consumer=findByPrincipal();
		Assert.notNull(consumer);
		ShoppingCart shopc=consumer.getShoppingCart();
		Assert.notNull(shopc);
		List<ShoppingCartComment> s=new ArrayList<ShoppingCartComment>();
		for(ShoppingCartComment c:shopc.getComment()){
			s.add(c);
		}
		s.set(s.indexOf(previous),comment);
		shopc.setComment(s);
		shoppingCartRepository.save(shopc);
	}
	public void createOrder(String name,String address){
		Consumer consumer=findByPrincipal();
		Assert.notNull(consumer);
		ShoppingCart shopc=consumer.getShoppingCart();
		Assert.notNull(shopc);
		orderService.createOrderByShoppingCart(null,name,address);
		}
	public Consumer registerConsumer(String name, String surname, String phone,String email, Integer customID, Integer shopID,Integer userId){
		Consumer result;
		/*Si hacemos esto:
		Consumer consumer=findByPrincipal();
		Assert.isTrue(consumer==null);
		Administrator administrator=administratorService.findByPrincipal();
		Assert.isTrue(administrator==null);
		
		*Soltara error porque, efectivamente no hay nadie conectado por tanto en el Assert
		*del find by principal soltara error, esto lo controlaremos en las vistas
		*/
		
		result=create();
		Assert.notNull(result);
		result.setCancelledOrders(0);
		result.setDeliveredOrders(0);
		result.setComment(new ArrayList<Comment>());
		result.setCustomization(customizationService.findOne(customID));
		result.setEmail(email);
		result.setName(name);
		result.setOrder(new ArrayList<Order>());
		result.setPhone(phone);
		result.setShoppingCart(shoppingCartService.findOne(shopID));
		result.setSurname(surname);
		result.setUserAccount(userAccountService.findOne(userId));
		result.setSystemFolder(new ArrayList<SystemFolder>());
		
		consumerRepository.save(result);
		return result;
	}
	public boolean AssertConsumerEqual(Order order) {
		// TODO Auto-generated method stub
		return order.getConsumer().getId()==findByPrincipal().getId();
	}

	public Collection<Order> getOrders() {
		// TODO Auto-generated method stub
		return findByPrincipal().getOrder();
	}
}


