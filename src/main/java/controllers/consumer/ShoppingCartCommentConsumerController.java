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

import services.ConsumerService;
import services.ShoppingCartCommentService;
import controllers.AbstractController;
import domain.Consumer;
import domain.ShoppingCartComment;

@Controller
@RequestMapping("/shoppingcartcomment/consumer")
public class ShoppingCartCommentConsumerController extends AbstractController {
	
	@Autowired
	private ShoppingCartCommentService shoppingCartCommentService;

	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ShoppingCartComment announcement;

		announcement = shoppingCartCommentService.create();
		result = createEditModelAndView(announcement);

		return result;
	}
	
	@RequestMapping(value = "/editComment", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int commentId) {
		ModelAndView result;
		ShoppingCartComment scItem;

		scItem = shoppingCartCommentService.findOne(commentId);		
		Assert.notNull(scItem);
		result = createEditModelAndView(scItem);

		return result;
	}
	@RequestMapping(value = "/editComment", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ShoppingCartComment scItem, BindingResult binding) {
		ModelAndView result;
		Consumer consumer;
		if (binding.hasErrors()) {
			result = createEditModelAndView(scItem);
		} else {
			try {
				consumer=consumerService.findByPrincipal();
				if(!consumer.getShoppingCart().getComment().contains(scItem)){
				shoppingCartCommentService.addComment(scItem, consumer.getShoppingCart());
				}else{
					shoppingCartCommentService.save(scItem);
				}
				result = new ModelAndView("redirect:/shoppingcart/consumer/shoppingcart.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(scItem, "sc.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/editComment", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ShoppingCartComment scItem, BindingResult binding) {
		ModelAndView result;

		try {			
			shoppingCartCommentService.delete(scItem);
			result = new ModelAndView("redirect:/shoppingcart/consumer/shoppingcart.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(scItem, "sc.commit.error");
		}

		return result;
	}
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(ShoppingCartComment scItem) {
		ModelAndView result;

		result = createEditModelAndView(scItem, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(ShoppingCartComment scItem, String message) {
		ModelAndView result;
		result = new ModelAndView("consumer/editComment");
		result.addObject("shoppingCartComment", scItem);
		result.addObject("message", message);

		return result;
	}
}
