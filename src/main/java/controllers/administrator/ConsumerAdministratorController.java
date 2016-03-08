package controllers.administrator;




import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ConsumerService;
import controllers.AbstractController;
import domain.Consumer;

@Controller
@RequestMapping("/consumer/administrator")
public class ConsumerAdministratorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private ConsumerService consumerService;
	
	// Constructors -----------------------------------------------------------
	
	public ConsumerAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView mav;
		Collection<Consumer> consumers;

		consumers = consumerService.findAll();

		mav = new ModelAndView("consumer/list");
		mav.addObject("consumers", consumers);
		mav.addObject("requestURI","/consumer/administrator/list.do");


		return mav;
	}
}
