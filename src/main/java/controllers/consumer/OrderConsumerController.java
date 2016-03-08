package controllers.consumer;


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

import services.ConsumerService;
import services.CreditCardService;
import services.OrderService;

import controllers.AbstractController;
import domain.Order;

@Controller
@RequestMapping("/order/consumer")
public class OrderConsumerController extends AbstractController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private CreditCardService creditCardService;
	
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView mav;
		Collection<Order> orders;

		orders = consumerService.getOrders();

		mav = new ModelAndView("order/ordersConsumer");
		mav.addObject("orders", orders);
		mav.addObject("requestURI","/order/consumer/list");
		return mav;
		}


	
	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/plaba", method = RequestMethod.GET)
	public ModelAndView editAssign(@RequestParam int orderId) {
		ModelAndView result;
		Order order;
		
		order = orderService.findOne(orderId);	
		Assert.notNull(order);
		result = createEditModelAndViewPlaba(order);

		return result;
	}

	@RequestMapping(value = "/plaba", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAssign(@Valid Order order, BindingResult binding) {
		ModelAndView result;
		order=orderService.findOne(order.getId());
		
			try {
				if( order.getCancelMoment()==null && order.getDeliverMoment()==null){
				
				orderService.usePlaba(order);
			
				result = new ModelAndView("redirect:/order/consumer/list.do");
				}else{
					result = createEditModelAndViewPlaba(order, "order.commit.error");
				}
			} catch (Throwable oops) {
				result = createEditModelAndViewPlaba(order, "order.commit.error");				
			}
		

		return result;
	}
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int orderId) {
		ModelAndView result;
		Order order;
		
		order = orderService.findOne(orderId);		
	
		result = createEditModelAndViewCancel(order);

		return result;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Order order, BindingResult binding) {
		ModelAndView result;
			order=orderService.findOne(order.getId());
			
			try {
				if(consumerService.AssertConsumerEqual(order)&& order.getClerk()==null
						&&order.getDeliverMoment()==null){
					
					
				order.setCancelMoment(new Date(System.currentTimeMillis()-1));
				
				orderService.save(order);
				
				result = new ModelAndView("redirect:/order/consumer/list.do");
				}else{
				result = createEditModelAndViewCancel(order, "order.commit.error");	
				}
				
			} catch (Throwable oops) {
				result = createEditModelAndViewCancel(order, "order.commit.error");				
			}
		

		return result;
	}
			
	
	

	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView editCreate(@RequestParam int shoppingCartId) {
		ModelAndView result;
		Order o=orderService.create();
		o.setConsumer(consumerService.findByPrincipal());
		o.setClerk(null);
		o.setPlacementMoment(new Date(System.currentTimeMillis()-1));
		o.setCreditCard(null);
		result = createEditModelAndView(o);		

		return result;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Order order, BindingResult binding) {
		ModelAndView result;
		
			try {
				Date date=new Date();
				if(order.getCreditCard().getExpeditionYear()>date.getYear()){
					if(order.getCreditCard().getExpeditionMonth()>=(date.getMonth()+1)){
				creditCardService.save(order.getCreditCard());
				order.setCreditCard(creditCardService.findAll().get(creditCardService.findAll().size()-1));
				orderService.createOrderByShoppingCart(order.getCreditCard(),order.getName(), order.getAddress());
				result = new ModelAndView("redirect:/shoppingcart/consumer/shoppingcart.do");
				}else{
					result = createEditModelAndView(order,"order.commit.error");

				}
				}else{
					result = createEditModelAndView(order,"order.commit.error");

				}
			} catch (Throwable oops) {

				result = createEditModelAndView(order,"order.commit.error");
			}
		

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Order o) {
		ModelAndView result;

		result = createEditModelAndView(o, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Order o, String message) {
		ModelAndView result;
		result = new ModelAndView("order/create");
		result.addObject("order",o);
		result.addObject("message", message);

		return result;
	}
	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndViewPlaba(Order order) {
		ModelAndView result;

		result = createEditModelAndViewPlaba(order, null);
		
		return result;
	}	
	protected ModelAndView createEditModelAndViewPlaba(Order order, String message) {
		ModelAndView result;
		
		result =new ModelAndView("order/plaba");
	
		result.addObject("order", order);
		result.addObject("message", message);

		return result;
	}
		protected ModelAndView createEditModelAndViewCancel(Order order) {
			ModelAndView result;

			result = createEditModelAndViewCancel(order, null);
			
			return result;
		}	
		
		protected ModelAndView createEditModelAndViewCancel(Order order, String message) {
			ModelAndView result;
			
			result =new ModelAndView("order/cancel");
		
			result.addObject("order", order);
			result.addObject("message", message);

			return result;
		}
}
