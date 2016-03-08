package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import services.ItemService;

import domain.Item;

@Controller
@RequestMapping("/item")
public class ItemController extends AbstractController {
	// Services
	@Autowired
	private ItemService itemService;



	public ItemController() {
		super();
	}

	// listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Item> notDeletedItems;
		notDeletedItems = itemService.findAllButDeleted();
		Collection<Item> deletedItems;
		deletedItems = itemService.findAllDeleted();
		result = new ModelAndView("item/list");
		result.addObject("items", notDeletedItems);
		result.addObject("deletedItems", deletedItems);
		result.addObject("requestURI", "/item/list.do");
		return result;
	}


	@RequestMapping(value = "/search", method = RequestMethod.POST, params="search")
	public ModelAndView search(String searchText) {
		Collection<Item> allFound = itemService.findOneBySearch(searchText);
		ModelAndView mav = new ModelAndView("item/search");
		mav.addObject("items", allFound);
		mav.addObject("requestURI", "/item/search.do");

		return mav;
	}
}
