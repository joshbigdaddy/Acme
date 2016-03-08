package controllers.clerk;

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

import services.ClerkService;
import services.OrderService;
import controllers.AbstractController;
import domain.Order;
import domain.OrderItem;

@Controller
@RequestMapping("/order/clerk")

public class OrderClerkController extends AbstractController{
	
		
		// Services ---------------------------------------------------------------

	
		@Autowired
		private ClerkService clerkService;
		@Autowired
		private OrderService orderService;
		
		// Constructors -----------------------------------------------------------
		
		public OrderClerkController() {
			super();
		}

		
		// Edition ----------------------------------------------------------------
		@RequestMapping(value = "/list")
		public ModelAndView list() {
			ModelAndView mav;
			Collection<Order> orders;

			orders = orderService.findAll();

			mav = new ModelAndView("order/list");
			mav.addObject("orders", orders);
			mav.addObject("requestURI","/order/clerk/list.do");

			return mav;
		}
		@RequestMapping(value = "/deliver", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int orderId) {
			ModelAndView result;
			Order order;
			
			order = orderService.findOne(orderId);		
			Assert.notNull(order);
			result = createEditModelAndViewDeliver(order);

			return result;
		}

		@RequestMapping(value = "/deliver", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Order order, BindingResult binding) {
			ModelAndView result;
				order=orderService.findOne(order.getId());
				try {
					if(clerkService.AssertClerkEqual(order)&& order.getCancelMoment()==null
							&&order.getDeliverMoment()==null){					
					order.setDeliverMoment(new Date(System.currentTimeMillis()-1));
					orderService.save(order);
					result = new ModelAndView("redirect:/order/clerk/list.do");
					}else{
					result = createEditModelAndViewDeliver(order, "order.commit.error");	
					}
					
				} catch (Throwable oops) {
					result = createEditModelAndViewDeliver(order, "order.commit.error");				
				}
			

			return result;
		}
				
		@RequestMapping(value = "/assign", method = RequestMethod.GET)
		public ModelAndView editAssign(@RequestParam int orderId) {
			ModelAndView result;
			Order order;
			
			order = orderService.findOne(orderId);	
			Assert.notNull(order);
			result = createEditModelAndViewAssign(order);

			return result;
		}

		@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "save")
		public ModelAndView saveAssign(@Valid Order order, BindingResult binding) {
			ModelAndView result;
			order=orderService.findOne(order.getId());
			
				try {
					if(order.getClerk()==null && order.getCancelMoment()==null && order.getDeliverMoment()==null){
					
					orderService.assignCurrentClerk(order);
				
					result = new ModelAndView("redirect:/order/clerk/list.do");
					}else{
						result = createEditModelAndViewAssign(order, "order.commit.error");
					}
				} catch (Throwable oops) {
					result = createEditModelAndViewAssign(order, "order.commit.error");				
				}
			

			return result;
		}
		@RequestMapping(value = "/seeItems", method = RequestMethod.GET)
		public ModelAndView seeItems(@RequestParam int orderId) {
			ModelAndView result;
			Order order;			
			order = orderService.findOne(orderId);		
			Assert.notNull(order);
			Collection<OrderItem> items=order.getOrderItems();
			result = new ModelAndView("order/listItems");
			result.addObject("items", items);
			result.addObject("requestURI", "/item/list.do");

			return result;
		}
		// Ancillary methods ------------------------------------------------------
		
		protected ModelAndView createEditModelAndViewAssign(Order order) {
			ModelAndView result;

			result = createEditModelAndViewAssign(order, null);
			
			return result;
		}	
		
		protected ModelAndView createEditModelAndViewAssign(Order order, String message) {
			ModelAndView result;
			
			result =new ModelAndView("order/assign");
		
			result.addObject("order", order);
			result.addObject("message", message);

			return result;
		}
		protected ModelAndView createEditModelAndViewDeliver(Order order) {
			ModelAndView result;

			result = createEditModelAndViewDeliver(order, null);
			
			return result;
		}	
		
		protected ModelAndView createEditModelAndViewDeliver(Order order, String message) {
			ModelAndView result;
			
			result =new ModelAndView("order/deliver");
		
			result.addObject("order", order);
			result.addObject("message", message);

			return result;
		}

	}


