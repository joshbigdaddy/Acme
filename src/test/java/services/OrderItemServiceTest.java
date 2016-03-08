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

import domain.OrderItem;



import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class OrderItemServiceTest extends AbstractTest{
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Test
	public void testCreate(){
		OrderItem order = orderItemService.create();
		System.out.println(order);
	}
	
	@Test
	public void testSave(){
		OrderItem o = new ArrayList<OrderItem>(orderItemService.findAll()).get(0);
		o.setName("Order Item test");
		orderItemService.save(o);
		o = new ArrayList<OrderItem>(orderItemService.findAll()).get(0);
		System.out.println(o.getName());
	}
	
	@Test
	public void testFindAll(){
		
		Collection<OrderItem> order = orderItemService.findAll();
		System.out.println(order);
	}
	
}
