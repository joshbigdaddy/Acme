package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


import domain.Item;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ItemServiceTest extends AbstractTest {
	
	@Autowired
	private ItemService itemService;

	
	@Test
	public void testFindAll(){
		
		Collection<Item> item;
		
		item = itemService.findAll();
		if(item!=null){
		for(Item i:item){
			System.out.println(i.getName());
		}
		}
		authenticate(null);
	}
	
	
	@Test
	public void testBestSellingItem(){
		
		Collection<Item> item;
		authenticate("admin1");
		item = itemService.bestSelling();
		if(item!=null){
		for(Item i:item){
			System.out.println(i.getName());
		}
		}
		authenticate(null);
	}
	@Test
	public void testWorstSellingItem(){
		
		Collection<Item> item1;
		authenticate("admin1");
		item1 = itemService.worstSelling();
		if(item1!=null){
		for(Item i:item1){
			System.out.println(i.getName());
		}
		}
		authenticate(null);
	}
	
	@Test
	public void testMoreComments(){
		
		Collection<Item> item2;
		
		item2 = itemService.moreComments();
		if(item2!=null){
		for(Item i:item2){
			System.out.println(i.getName());
		}
		}
		
	}
	@Test
	public void testgroupByCategory(){
		
		Collection<Item> item3;
		
		item3 = itemService.groupByCategory();
		if(item3!=null){
		for(Item i:item3){
			System.out.println(i.getName());
		}
		}
		
	}
	
	
}
