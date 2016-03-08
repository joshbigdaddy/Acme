package controllers.consumer;

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

import services.ShoppingCartItemService;

import domain.ShoppingCartItem;
@Controller
@RequestMapping("/shoppingcartitem/consumer")
public class ShoppingCartItemConsumerController extends AbstractController {
	@Autowired
	private ShoppingCartItemService shoppingCartItemService;
	
	@RequestMapping(value = "/editItem", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int itemId) {
		ModelAndView result;
		ShoppingCartItem scItem;

		scItem = shoppingCartItemService.findOne(itemId);		
		Assert.notNull(scItem);
		result = createEditModelAndView(scItem);

		return result;
	}
	@RequestMapping(value = "/editItem", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ShoppingCartItem scItem, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(scItem);
		} else {
			try {
				shoppingCartItemService.save(scItem);				
				result = new ModelAndView("redirect:/shoppingcart/consumer/shoppingcart.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(scItem, "sc.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/editItem", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ShoppingCartItem scItem, BindingResult binding) {
		ModelAndView result;

		try {			
			shoppingCartItemService.delete(scItem);
			result = new ModelAndView("redirect:/shoppingcart/consumer/shoppingcart.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(scItem, "sc.commit.error");
		}

		return result;
	}
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(ShoppingCartItem scItem) {
		ModelAndView result;

		result = createEditModelAndView(scItem, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(ShoppingCartItem scItem, String message) {
		ModelAndView result;
		result = new ModelAndView("consumer/editItem");
		result.addObject("shoppingCartItem", scItem);
		result.addObject("message", message);

		return result;
	}
}
