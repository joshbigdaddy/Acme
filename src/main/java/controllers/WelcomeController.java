/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Item;

import services.ItemService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private ItemService itemService;
	// Constructors -----------------------------------------------------------
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required=false) String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Collection<Item> bestSellingItems;
		bestSellingItems= itemService.bestSelling();
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
				
		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("bestSellingItems", bestSellingItems);
		result.addObject("moment", moment);
	
		

		return result;
	}
}