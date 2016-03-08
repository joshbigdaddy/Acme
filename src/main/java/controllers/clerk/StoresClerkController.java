package controllers.clerk;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Stores;
import domain.Warehouse;

import services.ItemService;
import services.StoresService;
import services.WarehouseService;


@Controller
@RequestMapping("/stores/clerk")
public class StoresClerkController {

	@Autowired
	private StoresService storesService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private ItemService itemService;
	// Constructors -----------------------------------------------------------
	public StoresClerkController() {
		super();
	}
	

	@RequestMapping(value = "/withDraw",method = RequestMethod.GET)
	public ModelAndView editStore(@RequestParam int storesId){
		ModelAndView result;
		Stores stores;
	
		stores = storesService.findOne(storesId);
		Assert.notNull(stores);
		result = createEditStoresModelAndView(stores);
		
		return result;		
	}
	@RequestMapping(value = "/withDraw", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Stores stores,BindingResult binding){
		ModelAndView result = null;
		Boolean i=true;
	
		//if(binding.hasErrors()){
	//		result = createEditModelAndView(warehouse);
	//	}else{
		try{
			
			Warehouse w= null;
			for(Warehouse war:warehouseService.findAll()){
				if(war.toString().equals(binding.getFieldValue("warehouse").toString())){
					w=war;
				}
			}
			stores.setWarehouse(w);			
			
			for(Stores s: storesService.findAll()){
				if(s.getItem()==stores.getItem()&&s.getWarehouse()==stores.getWarehouse()){
					if(stores.getUnits()>s.getUnits()){
						result = createEditStoresModelAndView(stores,"administrator.commit.withDraw");
						i=false;
						break;
					}
					stores.setId(s.getId());
					stores.getItem().setUnitsSold(stores.getItem().getUnitsSold()+stores.getUnits());
					stores.setUnits(s.getUnits()-stores.getUnits());
					break;
		     		}
		    	}
			if(i==true){
				warehouseService.changeCuantityOfStoredItem(stores.getWarehouse().getId(),stores.getItem(), stores.getUnits());
				
				itemService.save(stores.getItem());
				
				result = new ModelAndView("redirect:/warehouse/showItems.do?warehouseId="+ stores.getWarehouse().getId());
			}
			}catch(Throwable oops){
				
				result = createEditStoresModelAndView(stores,"administrator.commit.withDraw");
				
			}
//		}
		return result;
	}	

	
	
	protected ModelAndView createEditStoresModelAndView(Stores store){
		ModelAndView result;
		
		result = createEditStoresModelAndView(store,null);
		
		return result;
	}
	
	protected ModelAndView createEditStoresModelAndView(Stores stores, String message) {
		ModelAndView result;
		Stores stor = new Stores();
		stor.setItem(stores.getItem());
		stor.setWarehouse(stores.getWarehouse());
		//administrator = administratorService.findByPrincipal();
		
		result = new ModelAndView("stores/withDraw");
		result.addObject("requestURI","/stores/clerk/edit.do");
		result.addObject("stores",stor);
		result.addObject("message",message);
		//result.addObject("administrator",administrator);
		
		return result;
	}
	

}
