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

import utilities.AbstractTest;

import domain.ShoppingCart;
import domain.ShoppingCartItem;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ShoppingCartTest extends AbstractTest{
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Test
	public void testCreate() {
		ShoppingCart result=shoppingCartService.create();

		Assert.notNull(result);
	}
	
	@Test
	public void testFindAll() {
		Collection<ShoppingCart> result;

		result = shoppingCartService.findAll();
		System.out.println("---------------------TestFindAll------------------");
		for(ShoppingCart c: result){
			System.out.println(c);
		}
		System.out.println("--------------------------------------------------");
		
	}
	
	@Test
	public void testFindOne() {
		ShoppingCart result=shoppingCartService.findOne(34);
		Assert.notNull(result);

	}

	

	// Other business methods -------------------------------------------------

	@Test
	public void testGetItems(){
		authenticate("consumer");
		Collection<ShoppingCartItem> map=shoppingCartService.getItems();
		for(ShoppingCartItem i:map){
			System.out.println(i.getName()+" -> "+i.getQuantity()+" unidades");
		}
		authenticate(null);
		
	}
	//Tests borrados, se probaron en la anterior entrega, el método ha cambiado y se ha probado en las vistas
}
