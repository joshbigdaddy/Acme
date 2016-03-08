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

import domain.Category;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/datasource.xml","classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CategoryServiceTest extends AbstractTest {
	@Autowired
	private CategoryService categoryService;
	
	
	//Tests
	@Test
	public void testCreate() {
		authenticate("admin1");
		Category result;

		result = categoryService.create();

		Assert.notNull(result);
		authenticate(null);
	}
	@Test
	public void testFindOne() {
		Category result;

		result = categoryService.findOne(9);//category1
		Assert.notNull(result);

	}
	
	@Test
	public void testFindAll() {
		Collection<Category> result;

		result = categoryService.findAll();
		System.out.println("---------------------TestFindAll------------------");
		for(Category c: result){
			System.out.println(c.getName());
		}
		System.out.println("--------------------------------------------------");

	}
	
	@Test
	public void testShowAll() {
		authenticate("admin1");
		Collection<Category> result;

		result = categoryService.findAll();
		System.out.println("---------------------TestShowAll------------------");
		for(Category c: result){
			System.out.println(c.getName());
		}
		System.out.println("--------------------------------------------------");
		authenticate(null);
	}
	
	
	@Test
	public void testSave() {
		authenticate("admin1");
		Category category=categoryService.findOne(9);//category1
		category.setName("RandomName");
		categoryService.save(category);
		
		Assert.isTrue(categoryService.findOne(9).getName().equals("RandomName"));
		authenticate(null);
	}
	@Test
	public void testDelete() {
		authenticate("admin1");
		Category category=categoryService.findOne(11);//category3
		categoryService.delete(category);
		Category category2=categoryService.findOne(11);//category3
		
		Assert.isTrue(category2==null);
		authenticate(null);
	}
}