
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ItemService;
import services.ProviderService;
import domain.Actor;
import domain.Item;

@Controller
@RequestMapping("/item")
public class ItemController extends AbstractController {

	@Autowired
	private ItemService		itemService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ProviderService	providerService;


	@RequestMapping(value = "/anonymousList", method = RequestMethod.GET)
	public ModelAndView anonymousList(@RequestParam(required = false) final String searcher) {

		ModelAndView res;
		Collection<Item> items;
		String requestURI = "item/anonymousList.do";
		Collection<Item> searchedItems;

		items = this.itemService.findAll();

		if (searcher != null) {
			searchedItems = this.itemService.searcherItems(searcher);
			items.retainAll(searchedItems);
			requestURI += "?searcher" + searcher;
		}

		res = new ModelAndView("item/list");
		res.addObject("items", items);
		res.addObject("requestURI", requestURI);

		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String searcher) {

		ModelAndView res;
		Collection<Item> items;
		Actor principal;
		String requestURI = "item/list.do";
		Collection<Item> searchedItems;

		principal = this.actorService.findByPrincipal();

		if (this.providerService.findOne(principal.getId()) != null)
			items = this.itemService.getItemsByProvider();
		else
			items = this.itemService.findAll();

		if (searcher != null) {
			searchedItems = this.itemService.searcherItems(searcher);
			items.retainAll(searchedItems);
			requestURI += "?searcher" + searcher;
		}

		res = new ModelAndView("item/list");
		res.addObject("items", items);
		res.addObject("requestURI", requestURI);

		return res;
	}

	@RequestMapping(value = "/listByProvider", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer providerId) {

		ModelAndView res;
		Collection<Item> items;
		final String requestURI = "item/list.do";

		items = this.itemService.getItemsByProviderId(providerId);

		res = new ModelAndView("item/list");
		res.addObject("items", items);
		res.addObject("requestURI", requestURI);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Item i;

		i = this.itemService.create();
		res = this.createEditModelAndView(i);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int itemId) {
		ModelAndView res;
		Item i;

		i = this.itemService.findOneToEdit(itemId);
		res = this.createEditModelAndView(i);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Item i, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(i);
		} else
			try {
				this.itemService.save(i);
				res = new ModelAndView("redirect:/item/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(i, "item.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int itemId) {
		ModelAndView result;
		Item i;
		Collection<String> pictures;
		i = this.itemService.findOne(itemId);

		pictures = i.getPictures();

		result = new ModelAndView("item/show");
		result.addObject("item", i);
		result.addObject("itemid", itemId);
		result.addObject("pictures", pictures);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int itemId) {

		ModelAndView res;

		this.itemService.delete(itemId);

		res = new ModelAndView("redirect:/item/list.do");
		return res;
	}

	protected ModelAndView createEditModelAndView(final Item i) {
		ModelAndView res;

		res = this.createEditModelAndView(i, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Item i, final String messageCode) {
		ModelAndView res;

		if (i.getId() == 0)
			res = new ModelAndView("item/create");
		else
			res = new ModelAndView("item/edit");

		res.addObject("item", i);
		res.addObject("message", messageCode);

		return res;
	}

}
