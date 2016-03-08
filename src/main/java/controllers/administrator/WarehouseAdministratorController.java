package controllers.administrator;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import services.WarehouseService;

import controllers.AbstractController;

import domain.Warehouse;

@Controller
@RequestMapping("/warehouse/administrator")
public class WarehouseAdministratorController extends AbstractController{

	// Services ------------------------------------------------------------
	
		@Autowired
		private WarehouseService warehouseService;
		
		
		// Constructors -----------------------------------------------------------
	public WarehouseAdministratorController() {
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)	
	public ModelAndView createWarehouse(){
		Warehouse warehouse;
		ModelAndView result;
		
		warehouse = warehouseService.create();
		result = createWarehouseModelAndView(warehouse);
		
		return result;
	}
	
	//EDITION METHODS
	
		@RequestMapping(value = "/edit",method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int warehouseId){
			ModelAndView result;
			Warehouse warehouse;
			
			warehouse = warehouseService.findOne(warehouseId);
			Assert.notNull(warehouse);
			result = createEditModelAndView(warehouse);
			
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Warehouse warehouse,BindingResult binding){
			ModelAndView result;
			//if(binding.hasErrors()){
		//		result = createEditModelAndView(warehouse);
		//	}else{
				try{
				
					warehouseService.save(warehouse);
					result = new ModelAndView("redirect:http://localhost:8080/Acme-Supermarket/warehouse/list.do");
				
				}catch(Throwable oops){
					
					result = createEditModelAndView(warehouse,"administrator.commit.error");
				}
	//		}
			return result;
		}
		
		@RequestMapping(value = "/edit" , method = RequestMethod.POST,params = "delete")
		public ModelAndView delete(Warehouse warehouse,BindingResult binding){
			ModelAndView result;
			
			try{
				warehouseService.delete(warehouse);
				result = new ModelAndView("warehouse/list");
			} catch(Throwable oops) {
				result = createEditModelAndView(warehouse,"administrator.commit.error");
			}
			return result;
		}
		
	
		
		protected ModelAndView createEditModelAndView(Warehouse warehouse){
			ModelAndView result;
			
			result = createEditModelAndView(warehouse,null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Warehouse warehouse, String message) {
			ModelAndView result;
			
			
			//administrator = administratorService.findByPrincipal();
			
			result = new ModelAndView("warehouse/edit");
			
			result.addObject("requestURI","/warehouse/list.do");
			result.addObject("warehouse",warehouse);
			//result.addObject("administrator",administrator);
			
			return result;
		}
		protected ModelAndView createWarehouseModelAndView(Warehouse warehouse) {
			ModelAndView result;
			
			result = new ModelAndView("warehouse/edit");
			result.addObject("warehouse", warehouse);
			
			return result;
		}
		
	
}
