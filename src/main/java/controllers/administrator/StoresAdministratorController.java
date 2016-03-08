package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Item;
import domain.Stores;
import domain.Warehouse;

import services.ItemService;
import services.StoresService;
import services.WarehouseService;


@Controller
@RequestMapping("/stores/administrator")
public class StoresAdministratorController {

	@Autowired
	private StoresService storesService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private ItemService itemService;
	// Constructors -----------------------------------------------------------
	public StoresAdministratorController() {
		super();
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.GET)	
	public ModelAndView createWarehouse(@RequestParam int warehouseId){
		Stores stores;
		ModelAndView result;
		
		
		stores = storesService.create();
		stores.setWarehouse(warehouseService.findOne(warehouseId));
		System.out.println(stores.getWarehouse());
		result = createStoresModelAndView(stores);
		
		return result;
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public ModelAndView editStore(@RequestParam int storesId){
		ModelAndView result;
		Stores stores;
		
		stores = storesService.findOne(storesId);
		Assert.notNull(stores);
		result = createEditStoresModelAndView(stores);
		
		return result;		
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Stores stores,BindingResult binding){
		ModelAndView result;
		
	
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
			System.out.println(stores.getWarehouse());
			System.out.println(stores.getItem());
			System.out.println(stores.getUnits());
			for(Stores s: storesService.findAll()){
				if(s.getItem()==stores.getItem()&&s.getWarehouse()==stores.getWarehouse()){
					stores.setId(s.getId());
		     		}
		    	}
			System.out.println("1");
				if(stores.getId()==0){
					System.out.println("2");
				storesService.save(stores);
				System.out.println("3");
				}else{
					System.out.println("4");
				warehouseService.changeCuantityOfStoredItem(stores.getWarehouse().getId(),stores.getItem(), stores.getUnits());
				}
				result = new ModelAndView("redirect:/warehouse/showItems.do?warehouseId="+ stores.getWarehouse().getId());
				
			}catch(Throwable oops){
				
				result = createEditStoresModelAndView(stores,"administrator.commit.error");
				
			}
//		}
		return result;
	}	
	@RequestMapping(value = "/storesEdit",method = RequestMethod.GET, params = "changeQuantity")
	public ModelAndView changeQuantityOfItems(@Valid Item item,Integer id,Integer units){
		ModelAndView result;
		
		warehouseService.changeCuantityOfStoredItem(id,item, units);
		Stores stores = storesService.findOne(id);
		result= new ModelAndView("redirect:/warehouse/showItems.do?warehouseId="+ stores.getWarehouse().getId());
		
		return result;			
	}
	@RequestMapping(value = "/edit" , method = RequestMethod.POST,params = "delete")
	public ModelAndView delete(Stores stores,BindingResult binding){
		ModelAndView result;
		
		try{
			stores=storesService.findOne(stores.getId());
			storesService.delete(stores);
			result = new ModelAndView("redirect:/warehouse/showItems.do?warehouseId="+ stores.getWarehouse().getId());
		} catch(Throwable oops) {
			result = createEditStoresModelAndView(stores,"administrator.commit.error");
		}
		return result;
	}
	
	protected ModelAndView createEditStoresModelAndView(Stores store){
		ModelAndView result;
		
		result = createEditStoresModelAndView(store,null);
		
		return result;
	}
	
	protected ModelAndView createEditStoresModelAndView(Stores stores, String message) {
		ModelAndView result;
		
		Collection<Item> items = itemService.findAll();

		//administrator = administratorService.findByPrincipal();
		
		result = new ModelAndView("stores/edit");
		result.addObject("itemList",items);
		result.addObject("requestURI","/stores/administrator/edit.do");
		result.addObject("stores",stores);
		
		//result.addObject("administrator",administrator);
		
		return result;
	}
	
	protected ModelAndView createStoresModelAndView(Stores stores) {
		ModelAndView result;
		Collection<Item> items = itemService.findAll();

		result = new ModelAndView("stores/edit");
		result.addObject("itemList",items);
		result.addObject("stores", stores);
		System.out.println(stores.getWarehouse()+" hola");
		result.addObject("warehouse",stores.getWarehouse());

		return result;
	}
	
}
