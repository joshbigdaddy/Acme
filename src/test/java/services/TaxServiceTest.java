package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

import domain.Tax;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TaxServiceTest extends AbstractTest{
	//Service Under test=======================================================
	
		@Autowired
		private TaxService taxService;
		
		//ESTO PUEDE ESTAR SUJETO A CAMBIOS YA QUE EL ID PUEDE VARIAR SI AÑADIMOS COSAS

		@Test
		public void testCreate(){
			authenticate("admin1");
			Tax res=taxService.create();
			System.out.println(res);
			authenticate(null);
		}
		@Test
		public void testFindAll(){
			Collection<Tax> all=taxService.findAll();	
			for(Tax c:all){
				System.out.println(c.getName());
				}
			}
		@Test
		public void testSave(){
			authenticate("admin1");
		Integer taxId;
		taxId=18;
		Tax c=taxService.findOne(taxId);
		c.setName("Steel Ball Run Registration Tax");
		taxService.save(c);
		System.out.println("Se espera 'Steel Ball Run Registration Tax' como resultado. El resultado es: "
		+c.getName());
		authenticate(null);
		}
		@Test
		public void testDelete(){
			authenticate("admin1");
			Integer taxId;
			taxId=20;
			Tax c=taxService.findOne(taxId);
			taxService.delete(c);
			System.out.println("Debe estar borrado el comentario 1 cuyo texto era el editado antes");
			for(Tax com:taxService.findAll()){
				System.out.println(com.getName());
			}
			authenticate(null);
			}
}
