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


import domain.Item;
import domain.Stores;
import domain.Warehouse;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class WarehouseServiceTest extends AbstractTest{
	
	@Autowired
	private WarehouseService warehouseService;

	@Test
	public void testFindAll(){
		
		Collection<Warehouse> ware;
		
		ware = warehouseService.findAll();
		if(ware!=null){
		for(Warehouse i:ware){
			System.out.println(i.getName());
		}
		}
		
	}

	
	@Test
	public void testCreate(){
		authenticate("admin1");
		Warehouse ware;
		
		
		ware = warehouseService.create();
		if(ware!=null){
			System.out.println(ware);
		}
		authenticate(null);
	}

	
	@Test
	public void testSave(){
		authenticate("admin1");
		Warehouse i = new ArrayList<Warehouse>(warehouseService.findAll()).get(0);
		i.setName("Warehouse example");
		warehouseService.save(i);
		
		Warehouse i2 = new ArrayList<Warehouse>(warehouseService.findAll()).get(0);
		System.out.println(i2.getName());
		authenticate(null);
	}
	
	
	@Test
	public void testDelete(){
		Collection<Warehouse> ware;
		authenticate("admin1");
		Warehouse i = new ArrayList<Warehouse>(warehouseService.findAll()).get(0);
		warehouseService.delete(i);
		ware = warehouseService.findAll();
		
		System.out.println(ware.size());
		authenticate(null);
	}
	

	@Test
	public void testChangeCuantityOfStoredItem(){
		
		Warehouse i = new ArrayList<Warehouse>(warehouseService.findAll()).get(0);
		
		Stores s =  new ArrayList<Stores>(i.getStores()).get(0);
		Item it = s.getItem();
		System.out.println(s.getUnits());
		warehouseService.changeCuantityOfStoredItem(i.getId(), it, 50);
		System.out.println(s.getUnits());
		
	}
	
	@Test
	public void testWarehouseThatHaveItem(){
		Collection<Warehouse> ware;
		
		Warehouse i = new ArrayList<Warehouse>(warehouseService.findAll()).get(0);
		Stores s =  new ArrayList<Stores>(i.getStores()).get(0);
		Item it = s.getItem();
		
		ware = warehouseService.getWarehouseThatHaveItem(it);
		System.out.print(i.getName());
		System.out.println(ware);
		
	}
	
}
