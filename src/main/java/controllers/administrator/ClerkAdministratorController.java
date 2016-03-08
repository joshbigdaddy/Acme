package controllers.administrator;




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
import services.ClerkService;
import controllers.AbstractController;
import domain.Clerk;

@Controller
@RequestMapping("/clerk/administrator")
public class ClerkAdministratorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private ClerkService clerkService;
	@Autowired
	private UserAccountService userAccountService;
	
	
	// Constructors -----------------------------------------------------------
	
	public ClerkAdministratorController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		
		Clerk clerk;
		clerk=clerkService.create();
		ModelAndView result;

		result = createEditModelAndView(clerk);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
public ModelAndView save(@Valid Clerk clerk, BindingResult res) {
	ModelAndView result;
	
	try{
		if(clerk.getEmail().contains("@")){
		UserAccount userAccount= clerk.getUserAccount();
		Authority authority= new Authority();
		authority.setAuthority(Authority.CLERK);
		userAccount.addAuthority(authority);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String password=encoder.encodePassword(userAccount.getPassword(), null);
		userAccount.setPassword(password);
		userAccountService.save(userAccount);
		
		userAccount=userAccountService.findAll().get(userAccountService.findAll().size()-1);
		clerk.setUserAccount(userAccount);
		clerkService.save(clerk);
		result = new ModelAndView("redirect:/welcome/index.do");
		}else{
			result = createEditModelAndView(clerk,"clerk.commit.error");
		}
	}catch(Throwable oops){
		result = createEditModelAndView(clerk,"clerk.commit.error");
		result.addObject("userName", clerk.getUserAccount().getUsername());
		result.addObject("name", clerk.getName());
		result.addObject("surname", clerk.getSurname());
		result.addObject("email", clerk.getEmail());
		result.addObject("phone", clerk.getPhone());
	}
	
	return result;	
	
	}
		

	// Ancillary methods ------------------------------------------------------
	
			protected ModelAndView createEditModelAndView(Clerk clerk) {
				ModelAndView result;

				result = createEditModelAndView(clerk,null);
				
				return result;
			}	
			
			protected ModelAndView createEditModelAndView( Clerk clerk,String message) {
				ModelAndView result;
				
				result = new ModelAndView("clerk/create");
				result.addObject("clerk",clerk);
				result.addObject("message", message);

				return result;
			}
}
