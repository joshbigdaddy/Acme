/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Category;

import services.CategoryService;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Services ------------------------------------------------------------
	@Autowired
	private CategoryService categoryService;
	
	// Constructors -----------------------------------------------------------
	
	public CategoryAdministratorController() {
		super();
	}
	
	// Create Category
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)	
	public ModelAndView createCategory(){
		Category category;
		ModelAndView result;
		
		category = categoryService.create();
		result = createCategoryModelAndView(category);
		
		return result;
	}
	// Listing Category
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listCategory(){
		ModelAndView result;
		Collection<Category> categories;
		
		categories = categoryService.findAll();
		
		result = new ModelAndView("category/administrator/list");
		result.addObject("categories",categories);
		result.addObject("requestURI", "category/list.do");
		
		return result;
	}
	
	//EDITION METHODS
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId){
		ModelAndView result;
		Category category;
		
		category = categoryService.findOne(categoryId);
		Assert.notNull(category);
		result = createEditModelAndView(category);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Category category,BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result = createEditModelAndView(category);
		}else{
			try{
				categoryService.save(category);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(category,"category.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/edit" , method = RequestMethod.POST,params = "delete")
	public ModelAndView delete(Category category,BindingResult binding){
		ModelAndView result;
		
		try{
			categoryService.delete(category);
			result = new ModelAndView("redirect:list.do");
		} catch(Throwable oops) {
			result = createEditModelAndView(category,"categry.commit.error");
		}
		return result;
	}
	
	
	//ANCILLARY METHODS
	
	protected ModelAndView createCategoryModelAndView(Category category) {
		ModelAndView result;
		
		result = new ModelAndView("category/administrator/edit");
		result.addObject("category", category);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Category category){
		ModelAndView result;
		
		result = createEditModelAndView(category,null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Category category, String message) {
		ModelAndView result;
		
		
		
		result = new ModelAndView("category/administrator/edit");
		result.addObject("requestURI","/category/administrator/edit.do");
		result.addObject("category",category);
		
		return result;
	}
	
}