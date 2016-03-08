package controllers.consumer;

import java.util.Collection;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConsumerService;
import services.ItemService;
import services.ShoppingCartService;
import controllers.AbstractController;
import domain.Item;
import domain.ShoppingCartComment;
import domain.ShoppingCartItem;
import domain.Consumer;
import domain.ShoppingCart;

@Controller
@RequestMapping("/shoppingcart/consumer")
public class ShoppingCartConsumerController extends AbstractController {
	
	// Services ---------------------------------------------------------------


	@Autowired
	private ItemService itemService;
	
	
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	@Autowired
	private ConsumerService consumerService;
	
	// Constructors -----------------------------------------------------------
	
	public ShoppingCartConsumerController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		ShoppingCart shoppingCart;
		Consumer consumer;
		Collection<ShoppingCartComment> comments;
		Collection<ShoppingCartItem> items;
		consumer = consumerService.findByPrincipal();
		shoppingCart=consumer.getShoppingCart();
		comments= shoppingCart.getComment();
		items=shoppingCart.getItem();
		result = new ModelAndView("consumer/shoppingcart");
		result.addObject("shoppingcart",shoppingCart);
		result.addObject("items", items);
		result.addObject("comments", comments);
		result.addObject("requestURI","/shoppingcart/consumer/shoppingcart");


		return result;
	}
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int itemId) {
		ModelAndView result;
		Item item;
		item = itemService.findOne(itemId);	
		
		result = createEditModelAndView(item);

		return result;
	}
	@RequestMapping(value = "/addItem", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Item scItem, BindingResult binding) {
		ModelAndView result;
		
			try {
				
				shoppingCartService.addItem(scItem);
				
				
				result = new ModelAndView("redirect:/shoppingcart/consumer/shoppingcart.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(scItem, "sc.commit.error");				
			}
		

		return result;
	}

	

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------
	protected ModelAndView createEditModelAndView(Item scItem) {
		ModelAndView result;

		result = createEditModelAndView(scItem, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Item scItem, String message) {
		ModelAndView result;
		result = new ModelAndView("consumer/addItem");
		result.addObject("item", scItem);
		result.addObject("message", message);

		return result;
	}
}
