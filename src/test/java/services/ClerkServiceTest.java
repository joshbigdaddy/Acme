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

import domain.Clerk;
import domain.Order;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ClerkServiceTest extends AbstractTest {
	@Autowired
	private ClerkService clerkService;
	@Autowired
	private OrderService orderService;
	
	//Tests
	@Test
	public void testCreate() {
		Clerk result;

		result = clerkService.create();

		Assert.notNull(result);
	}
	
	@Test
	public void testFindAll() {
		Collection<Clerk> result;

		result = clerkService.findAll();
		System.out.println("---------------------TestFindAll------------------");
		for(Clerk c: result){
			System.out.println(c.getName());
		}
		System.out.println("--------------------------------------------------");

	}
	
	@Test
	public void testFindOne() {
		Clerk result;

		result = clerkService.findOne(46);//clerk 2
		Assert.notNull(result);

	}
	
	@Test
	public void testSave() {
		Clerk clerk=clerkService.findOne(46);//clerk 2
		clerk.setName("RandomName");
		clerkService.save(clerk);
		
		Assert.isTrue(clerkService.findOne(46).getName().equals("RandomName"));
		
	}
	@Test
	public void testDelete() {
		Clerk clerk=clerkService.findOne(47);//clerk 3
		clerkService.delete(clerk);
		Clerk clerk2=clerkService.findOne(47);//clerk 3
		
		Assert.isTrue(clerk2==null);
	}
	
	
	// Other business methods -------------------------------------------------
	@Test
	public void testClerkMoreDeliveredOrders(){
		
		Collection<Clerk> all;
		authenticate("admin1");
		all= clerkService.clerkMoreDeliveredOrders();
		System.out.println("---------------------TestClerkMoreDeliveredOrders------------------");
		for(Clerk c: all){
			System.out.println(c.getName());
		}
		System.out.println("--------------------------------------------------");
		authenticate(null);
	
}
	@Test
public void testClerkLessDeliveredOrders(){
		
		Collection<Clerk> all;
		authenticate("admin1");
		all= clerkService.clerkLessDeliveredOrders();
		System.out.println("---------------------TestClerkMoreDeliveredOrders------------------");
		for(Clerk c: all){
			System.out.println(c.getName());
		}
		System.out.println("--------------------------------------------------");
		authenticate(null);
	
}
@Test
public void testAssignOrder(){
	authenticate("clerk1");
	Order o=orderService.findOne(51);//order4
	Clerk c= clerkService.findOne(45);//clerk1
	Assert.notNull(c);
	clerkService.assignOrder(o);
	Assert.isTrue(c.getOrders().contains(o));
	authenticate(null);
}
@Test
public void testMarkAsDeliered(){
	authenticate("clerk2");
	Order o=orderService.findOne(49); // order 2
	clerkService.markAsDelivered(o);
	Order o1=orderService.findOne(49);
	Assert.isTrue(o1.getDeliverMoment()!=null);
	authenticate(null);
}	

}