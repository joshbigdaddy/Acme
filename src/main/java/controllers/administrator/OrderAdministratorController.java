package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.OrderService;
import domain.Order;

@Controller
@RequestMapping("/order/administrator")
public class OrderAdministratorController {
	// Services ---------------------------------------------------------------

	@Autowired
	private OrderService orderService;
	
	// Constructors -----------------------------------------------------------
	
	public OrderAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView mav;
		Collection<Order> orders;

		orders = orderService.findAll();

		mav = new ModelAndView("order/list");
		mav.addObject("orders", orders);
		mav.addObject("requestURI","/order/administrator/list.do");

		return mav;
		}
}
