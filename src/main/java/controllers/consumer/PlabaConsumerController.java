/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.consumer;

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
import domain.Plaba;
import services.PlabaService;

@Controller
@RequestMapping("/plaba/consumer")
public class PlabaConsumerController extends AbstractController {

	// Services ------------------------------------------------------------
	@Autowired
	private PlabaService plabaService;
	
	
	
	// Constructors -----------------------------------------------------------
	
	public PlabaConsumerController() {
		super();
	}
	
	
	// Listing Plaba
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPlaba(){
		ModelAndView result;
		Collection<Plaba> plabas;
		
		plabas = plabaService.findAll();
		
		result = new ModelAndView("plaba/consumer/list");
		result.addObject("plaba",plabas);
		result.addObject("requestURI", "plaba/consumer/list.do");
		
		return result;
	}
	
	
	
	//ANCILLARY METHODS
	
	protected ModelAndView createModelAndView(Plaba plaba) {
		ModelAndView result;
		
		result = new ModelAndView("plaba/consumer/edit");
		result.addObject("plaba", result);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Plaba plaba){
		ModelAndView result;
		
		result = createEditModelAndView(plaba,null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Plaba plaba, String message) {
		ModelAndView result;
		result = new ModelAndView("plaba/consumer/edit");
		result.addObject("requestURI","/plaba/consumer/edit.do");
		result.addObject("plaba",plaba);
		
		return result;
	}
	
}
