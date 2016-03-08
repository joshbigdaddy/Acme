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
import java.util.Date;

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
import domain.Consumer;
import domain.Order;
import domain.Plaba;
import services.ConsumerService;
import services.PlabaService;

@Controller
@RequestMapping("/plaba/administrator")
public class PlabaAdministratorController extends AbstractController {

	// Services ------------------------------------------------------------
	@Autowired
	private PlabaService plabaService;
	@Autowired
	private ConsumerService consumerService;
	
	
	// Constructors -----------------------------------------------------------
	
	public PlabaAdministratorController() {
		super();
	}
	
	// Create Plaba
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)	
	public ModelAndView createPlaba(){
		Plaba plaba;
		ModelAndView result;
		
		plaba = plabaService.create();
		result = createModelAndView(plaba);
		
		return result;
	}
	// Listing Plaba
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPlaba(){
		ModelAndView result;
		Collection<Plaba> plabas;
		
		plabas = plabaService.findAll();
		
		result = new ModelAndView("plaba/administrator/list");
		result.addObject("plaba",plabas);
		result.addObject("requestURI", "plaba/administrator/list.do");
		
		return result;
	}
	
	//EDITION METHODS
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int plabaId){
		ModelAndView result;
		Plaba plaba;
		
		plaba = plabaService.findOne(plabaId);
		Assert.notNull(plaba);
		
		result = createEditModelAndView(plaba);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Plaba plaba,Consumer consumer,BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result = createEditModelAndView(plaba);
		}else{
			try{
			    if(consumer!=null){
				consumer=consumerService.findOne(consumer.getId());
				plaba.setConsumer(consumer);
			    }
				plabaService.save(plaba);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(plaba,"plaba.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/edit" , method = RequestMethod.POST,params = "delete")
	public ModelAndView delete(Plaba plaba,BindingResult binding){
		ModelAndView result;
		
		try{
			plabaService.delete(plaba);
			result = new ModelAndView("redirect:list.do");
		} catch(Throwable oops) {
			result = createEditModelAndView(plaba,"plaba.commit.error");
		}
		return result;
	}
	
/*	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAssign(@Valid Plaba plaba,Consumer consumer, BindingResult binding) {
		ModelAndView result;
		plaba=plabaService.findOne(plaba.getId());
		Date d=new Date(System.currentTimeMillis()-1);
			try {
				if(plaba.getConsumer()==null && (plaba.getValidDate()==null||plaba.getValidDate().compareTo(d)>0)){
				
				plabaService.assignConsumer(plaba,consumer);
			
				result = new ModelAndView("redirect:/plaba/administrator/list.do");
				}else{
					result = createEditModelAndViewAssign(plaba, "plaba.commit.error");
				}
			} catch (Throwable oops) {
				result = createEditModelAndViewAssign(plaba, "plaba.commit.error");				
			}
		

		return result;
	}*/
	//ANCILLARY METHODS
	
	protected ModelAndView createModelAndView(Plaba plaba) {
		ModelAndView result;
		Collection<Consumer> consumers;
		consumers= consumerService.findAll();
		result = new ModelAndView("plaba/administrator/edit");
		result.addObject("plaba", result);
		result.addObject("consumers",consumers);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Plaba plaba){
		ModelAndView result;
		
		result = createEditModelAndView(plaba,null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Plaba plaba, String message) {
		ModelAndView result;
		result = new ModelAndView("plaba/administrator/edit");
		result.addObject("requestURI","/plaba/administrator/edit.do");
		result.addObject("plaba",plaba);
		
		return result;
	}
	
}
