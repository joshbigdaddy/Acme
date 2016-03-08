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

import services.CategoryService;
import services.PlabaService;
import services.ItemService;
import services.TaxService;

import controllers.AbstractController;
import domain.Category;
import domain.Plaba;
import domain.Comment;
import domain.Item;
import domain.Tax;

@Controller
@RequestMapping("/item/administrator")
public class ItemAdministratorController extends AbstractController {
	// Services
	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PlabaService clase1ControlCheckService;
	@Autowired
	private TaxService taxService;

	public ItemAdministratorController() {
		super();
	}

	// creation
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Item item;

		item = itemService.create();
		result = createEditModelAndView(item);

		return result;
	}

	// edition
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int itemId) {
		ModelAndView result;
		Item item;
		item = itemService.findOne(itemId);
		Assert.notNull(item);
		result = createEditModelAndView(item);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Item item, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(item);
		} else {
			try {
				itemService.save(item);
				result = new ModelAndView(
						"redirect:/item/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(item, "item.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Item item, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(item);
		} else {
			try {
				itemService.delete(item);
				result = new ModelAndView(
						"redirect:/item/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(item, "item.commit.error");
			}
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Item item) {
		ModelAndView result;

		result = createEditModelAndView(item, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Item item, String message) {
		ModelAndView result;
		Collection<Category> categories;
		Collection<Plaba> Clase1ControlCheck;
		Collection<Tax> taxes;
		Collection<Comment> comments;
		categories = categoryService.findAll();
		Clase1ControlCheck = clase1ControlCheckService.findAll();
		taxes = taxService.findAll();
		comments = item.getComment();
		result = new ModelAndView("item/edit");
		result.addObject("item", item);
		result.addObject("categories", categories);
		result.addObject("clase1ControlCheck", Clase1ControlCheck);
		result.addObject("taxes", taxes);
		result.addObject("comments", comments);
		result.addObject("message", message);

		return result;

	}
	/*
	 * protected ModelAndView createEditModelAndView(Item item, String message)
	 * { ModelAndView result; result = new ModelAndView("item/edit");
	 * result.addObject("Item", item); result.addObject("message", message);
	 * 
	 * return result; }
	 */
}
