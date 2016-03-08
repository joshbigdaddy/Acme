package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Consumer;
import domain.ShoppingCart;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ConsumerServiceTest extends AbstractTest {
	@Autowired
	private ConsumerService consumerService;
	
	@Test
	public void testCreate() {
		Consumer result;

		result = consumerService.create();

		Assert.notNull(result);
	}
	
	@Test
	public void testFindAll() {
		Collection<Consumer> result;

		result = consumerService.findAll();
		System.out.println("---------------------TestFindAll------------------");
		for(Consumer c: result){
			System.out.println(c.getName()+" -> "+c.getId());
		}
		System.out.println("--------------------------------------------------");

	}
	@Test
	public void testFindOne() {
		Consumer result;

		result = consumerService.findOne(38);
		Assert.notNull(result);

	}
	@Test
	public void testSave() {
		Consumer consumer=consumerService.findOne(38);
		consumer.setName("nombreRandom");
		consumerService.save(consumer);
		
		Assert.isTrue(consumerService.findOne(38).getName().equals("nombreRandom"));
		
	}

	@Test
	public void testDelete() {
		Consumer consumer=consumerService.findOne(40);
		System.out.println(consumer.getId());
		consumerService.delete(consumer);
		Consumer consumer2=consumerService.findOne(40);
		
		Assert.isTrue(consumer2==null);
	}

	// Other business methods -------------------------------------------------
	
	@Test
	public void testFindConsumerMoreOrders(){
		authenticate("admin1");
		Collection<Consumer> result;
		
		result=consumerService.findConsumerMoreOrders();
		Assert.notNull(result);
		for(Consumer c:result){
			System.out.println(c.getName());
		}
		authenticate(null);
	}
	@Test
	public void testFindConsumerMoreMoney(){
		authenticate("admin1");
		Collection<Consumer> result;
		
		result=consumerService.findConsumerMoreMoney();
		
		Assert.notNull(result);
		for(Consumer c:result){
			System.out.println(c.getName());
		}
		authenticate(null);
		
	}
	@Test
	public void testConsumerMoreCancelledOrders(){
		authenticate("admin1");
		Collection<Consumer> result;
		
		result=consumerService.consumerMoreCancelledOrders();
		
		Assert.notNull(result);
		for(Consumer c:result){
			System.out.println(c.getName());
		}
		authenticate(null);
	}
	@Test
	public void testConsumerLessCancelledOrders(){
		authenticate("admin1");
		Collection<Consumer> result;
		
		result=consumerService.consumerLessCancelledOrders();
		
		Assert.notNull(result);
		for(Consumer c:result){
			System.out.println(c.getName());
		}
		authenticate(null);
	}
	@Test
	public void testShowShoppingcart(){
		authenticate("consumer");

		ShoppingCart shopcart=consumerService.showShoppingcart();
		Assert.notNull(shopcart);
		System.out.println(shopcart);
		
		authenticate(null);

	}
	//Create Order no se testea ya que se probara en el test de Order indirectamente
	@Test
	public void testRegisterConsumer(){
		Consumer consumer=consumerService.registerConsumer("namerandom","surnamerandom","897324987","emailrandom@random.com",24,37,7);
		Assert.notNull(consumer);
		Collection<Consumer> col=consumerService.findAll();
		for(Consumer c: col){
			System.out.println(c.getName() + " -> "+ c.getId());
		}
	}
	
}
