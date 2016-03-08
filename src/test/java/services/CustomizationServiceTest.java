package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Customization;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CustomizationServiceTest extends AbstractTest {
	@Autowired
	private CustomizationService customizationService;
	
	@Test
	public void testCreate(){
		Customization result;

		result = customizationService.create();		

		Assert.notNull(result);
	}
	@Test
	public void testFindOne() {
		Customization result;

		result = customizationService.findOne(22);

		Assert.notNull(result);
	}
	@Test
	public void testSave(){
		Customization result;

		result = customizationService.findOne(22);
		result.setName("customrandom");
		customizationService.save(result);
		Customization result2 = customizationService.findOne(22);
		Assert.isTrue(result2.getName().equals("customrandom"));
	}

}
