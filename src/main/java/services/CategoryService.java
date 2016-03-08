package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Category;

import repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
//Supporting Services

	
	//Constructor
	
	public CategoryService(){
		super();
	}

	
	public Category create(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Category result;
		result= new Category();
		return result;
	}
	public Category findOne(int categoryId) {
		Category result;

		result = categoryRepository.findOne(categoryId);
	

		return result;
	}
	
	public Collection<Category> findAll(){
		return categoryRepository.findAll();
	}
		
	public Collection<Category> showAll(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		return categoryRepository.findAll();
	}
	public void save(Category c){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		categoryRepository.save(c);
	}
	public void delete(Category c){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		categoryRepository.delete(c);
		
	}
	
}
	