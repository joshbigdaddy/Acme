package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ClerkService;
import services.ConsumerService;
import services.ItemService;
import services.OrderService;

import controllers.AbstractController;
import domain.Clerk;
import domain.Consumer;
import domain.Item;

@Controller
@RequestMapping("/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController{
	
	//SERVICES ------------------------------------------

	
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ClerkService clerkService;
	@Autowired
	private OrderService orderService;

	//Constructor
	
	public DashboardAdministratorController() {
		super();
	}
	
	//Methods
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard(){
		ModelAndView result;
		Collection<Consumer> consumerMoreOrders;
		Collection<Consumer> consumerMoreMoney;
		Collection<Item> bestSelling;
		Collection<Item> worstSelling;
		Collection<Clerk> clerkMoreOrders;
		Collection<Clerk> clerkLessOrders;
		Collection<Consumer> consumerMoreCancelled;
		Collection<Consumer> consumerLessCancelled;
		Long ordersCancelled;
		Collection<Item> itemMoreComments;
		
		consumerMoreOrders = consumerService.findConsumerMoreOrders();
		consumerMoreMoney = consumerService.findConsumerMoreMoney();
		bestSelling = itemService.bestSelling();
		worstSelling = itemService.worstSelling();
		clerkMoreOrders = clerkService.clerkMoreDeliveredOrders();
		clerkLessOrders = clerkService.clerkLessDeliveredOrders();
		consumerMoreCancelled = consumerService.consumerMoreCancelledOrders();
		consumerLessCancelled = consumerService.consumerLessCancelledOrders();
		ordersCancelled = orderService.ratioOrdersCancelledMonth();
		itemMoreComments = itemService.moreComments();
		
		result= createDashboardModelAndView(consumerMoreOrders,consumerMoreMoney,bestSelling,worstSelling,clerkMoreOrders,clerkLessOrders,consumerMoreCancelled,consumerLessCancelled,ordersCancelled,itemMoreComments);
		return result;
	}

	protected ModelAndView createDashboardModelAndView(Collection<Consumer> cOrders,Collection<Consumer> cMoney,Collection<Item> best,Collection<Item> worst,Collection<Clerk> clerkMOrders,
			Collection<Clerk> clerkLOrders,Collection<Consumer> cMoreCancelled,Collection<Consumer> cLessCancelled,Long ordersCancelled,Collection<Item> iMoreComments){
		ModelAndView result;
		
		result = new ModelAndView("administrator/dashboard");
		
		result.addObject("bestSellingItem",best);
		
		
		result.addObject("worstSellingItem", worst);
		
		result.addObject("moreOrders", cOrders);
		result.addObject("morePrice", cMoney);
		result.addObject("clerkMoreOrders", clerkMOrders);
		result.addObject("clerkLessOrders", clerkLOrders);
		result.addObject("consumerMoreCancelled", cMoreCancelled);
		result.addObject("consumerLessCancelled", cLessCancelled);
		result.addObject("ordersCancelled", ordersCancelled);
		result.addObject("itemsMoreComments", iMoreComments);
		
		
		return result;
	}
}
