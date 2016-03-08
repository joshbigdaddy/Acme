package services;


import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.Order;



import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class OrderServiceTest extends AbstractTest{
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void testCreate(){
		Order o = orderService.create();
		System.out.println(o);
	}
	
	@Test
	public void testFindAll(){
		Collection<Order> order = orderService.findAll();
		System.out.println(order);
	}
	@Test
	public void testShowAll(){
		authenticate("admin1");
		Collection<Order> order = orderService.showAll();
		System.out.println(order);
		authenticate(null);
	}
	
	@Test
	public void testSave(){
		Order o = new ArrayList<Order>(orderService.findAll()).get(0);
		o.setName("Order test");
		orderService.save(o);
		o = new ArrayList<Order>(orderService.findAll()).get(0);
		System.out.println(o.getName());
	}
	
	@Test
	public void testDelete(){
		Order o = new ArrayList<Order>(orderService.findAll()).get(0);
		orderService.delete(o);
		if(!orderService.findAll().contains(o)){
			System.out.println("Order not found");
		}
		
	}
	
	@Test
	public void testOrdersCancelledMonth(){
		System.out.println(orderService.ratioOrdersCancelledMonth());
	}
	
	
	
	
	@Test
	public void testCancelOrder(){
		authenticate("consumer");
		Order orde = new ArrayList<Order>(orderService.findAll()).get(2);
		orderService.cancelOrder(orde);
		System.out.println(new ArrayList<Order>(orderService.findAll()).get(2).getCancelMoment());
		authenticate(null);
	}
	
}
