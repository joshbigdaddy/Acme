package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Stores;
import domain.Warehouse;

import services.WarehouseService;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController extends AbstractController{

	@Autowired
	private WarehouseService warehouseService;
	
	
	// Constructors -----------------------------------------------------------
	public WarehouseController() {
		super();
	}
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listWarehouse(){
		ModelAndView result;
		Collection<Warehouse> warehouse;
		
		warehouse = warehouseService.findAll();
		
		result = new ModelAndView("warehouse/list");
		result.addObject("warehouse",warehouse);
		result.addObject("requestURI", "warehouse/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/showItems", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int warehouseId) {
		ModelAndView result;
		Warehouse w;
		w = warehouseService.findOne(warehouseId);
		Assert.notNull(w);
		result = createEditModelAndView(w.getStores(),w.getId());
		return result;
	}
	
	// Ancillary methods ------------------------------------------------------

			protected ModelAndView createEditModelAndView(Collection<Stores> stores,int id) {
				ModelAndView result;

				result = createEditModelAndView(stores,id, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(Collection<Stores> stores,int id, String message) {
				ModelAndView result;
				
				result = new ModelAndView("warehouse/showItems");
				result.addObject("stores", stores);
				result.addObject("warehouse",id);
				result.addObject("message", message);

				return result;

			}
}
