package services;


import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.Administrator;





import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class AdministratorServiceTest extends AbstractTest{
	
	@Autowired
	private AdministratorService administratorService;
	
	@Test
	public void testCreate(){
		System.out.println(administratorService.create());
	}
	
	@Test
	public void testFindAll(){
	for(Administrator a : administratorService.findAll()){
		System.out.println(a);
	}
	}
	
	@Test
	public void testSave(){
		Administrator a = new ArrayList<Administrator>(administratorService.findAll()).get(0);
		a.setName("Ejemplo");
		administratorService.save(a);
		System.out.println(new ArrayList<Administrator>(administratorService.findAll()).get(0));
	}
	
	@Test
	public void testDelete(){
		System.out.println(administratorService.findAll().size());
		Administrator a = new ArrayList<Administrator>(administratorService.findAll()).get(0);
		administratorService.delete(a);
		System.out.println(administratorService.findAll().size());
	}
	
}