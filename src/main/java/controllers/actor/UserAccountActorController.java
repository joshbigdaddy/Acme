package controllers.actor;





import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import services.ConsumerService;
import services.ShoppingCartService;
import controllers.AbstractController;
import domain.Consumer;
import domain.ShoppingCart;

@Controller
@RequestMapping("/userAccount/actor")
public class UserAccountActorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private ShoppingCartService shoppingCartService;

	
	// Constructors -----------------------------------------------------------
	
	public UserAccountActorController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		
		Consumer consumer;
		consumer=consumerService.create();
		ModelAndView result;

		result = createEditModelAndView(consumer);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
public ModelAndView save(@Valid Consumer consumer, BindingResult res) {
	ModelAndView result;
	
	try{
		if(consumer.getEmail().contains("@")){
		UserAccount userAccount= consumer.getUserAccount();
		Authority authority= new Authority();
		authority.setAuthority(Authority.CONSUMER);
		userAccount.addAuthority(authority);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String password=encoder.encodePassword(userAccount.getPassword(), null);
		userAccount.setPassword(password);
		userAccountService.save(userAccount);
		
		userAccount=userAccountService.findAll().get(userAccountService.findAll().size()-1);
		consumer.setUserAccount(userAccount);
		ShoppingCart shop= shoppingCartService.create();
		shoppingCartService.save(shop);
		shop=shoppingCartService.findAll().get(shoppingCartService.findAll().size()-1);
		consumer.setShoppingCart(shop);
		consumerService.save(consumer);
		result = new ModelAndView("redirect:/welcome/index.do");
		}else{
			result = createEditModelAndView(consumer,"consumer.commit.error");
		}
	}catch(Throwable oops){
		result = createEditModelAndView(consumer,"consumer.commit.error");
		result.addObject("userName", consumer.getUserAccount().getUsername());
		result.addObject("name", consumer.getName());
		result.addObject("surname", consumer.getSurname());
		result.addObject("email", consumer.getEmail());
		result.addObject("phone", consumer.getPhone());
	}
	
	return result;	
	
	}
		

	// Ancillary methods ------------------------------------------------------
	
			protected ModelAndView createEditModelAndView(Consumer consumer) {
				ModelAndView result;

				result = createEditModelAndView(consumer,null);
				
				return result;
			}	
			
			protected ModelAndView createEditModelAndView( Consumer consumer,String message) {
				ModelAndView result;
				
				result = new ModelAndView("userAccount/create");
				result.addObject("consumer",consumer);
				result.addObject("message", message);

				return result;
			}
}
