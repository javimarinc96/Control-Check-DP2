
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ItemRepository;
import domain.Actor;
import domain.Item;
import domain.Provider;

@Service
@Transactional
public class ItemService {

	//Managed repository -----------------------------------------------------------
	@Autowired
	private ItemRepository	itemRepository;

	//Supported services -----------------------------------------------------------
	@Autowired
	private ProviderService	providerService;

	@Autowired
	private ActorService	actorService;


	//Constructor ------------------------------------------------------------------
	public ItemService() {
		super();
	}

	//Simple CRUD methods ----------------------------------------------------------
	public Item create() {
		Item result;
		Actor principal;

		result = new Item();

		// Principal must be a Provider
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Provider.class, principal);

		final Provider p = this.providerService.findByPrincipal();
		final Collection<String> pictures = new ArrayList<String>();

		result.setProvider(p);
		result.setPictures(pictures);

		return result;
	}

	public Item save(final Item item) {
		Assert.notNull(item);
		Actor principal;

		// Principal must be a Provider
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Provider.class, principal);

		this.checkItem(item);

		if (item.getId() != 0)
			Assert.isTrue(principal.equals(item.getProvider()));

		return this.itemRepository.save(item);
	}

	public void delete(final int itemId) {

		Actor principal;

		final Item i = this.findOne(itemId);

		Assert.notNull(i);

		// Principal must be a Provider
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Provider.class, principal);

		Assert.isTrue(principal.equals(i.getProvider()));

		this.itemRepository.delete(i);

	}

	public Collection<Item> getItemsByProvider() {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Provider.class, logueado);
		return this.itemRepository.findAllItemsByProvider(logueado.getId());
	}

	public Collection<Item> getItemsByProviderId(final Integer providerId) {
		final Actor provider = this.providerService.findOne(providerId);
		Assert.isInstanceOf(Provider.class, provider);
		return this.itemRepository.findAllItemsByProvider(provider.getId());
	}

	public Item findOne(final int id) {
		final Item result = this.itemRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Item> findAll() {
		final Collection<Item> result = this.itemRepository.findAll();

		return result;
	}

	//Other business methods -------------------------------------------------------
	public void flush() {
		this.itemRepository.flush();
	}

	public void checkItem(final Item item) {
		boolean check = true;

		if (item.getName() == null || item.getDescription() == null || item.getLink() == null || item.getPictures() == null)
			check = false;

		Assert.isTrue(check);
	}

	public Item findOneToEdit(final int id) {
		Actor principal;

		// Principal must be a Provider
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Provider.class, principal);

		final Item result = this.itemRepository.findOne(id);

		Assert.notNull(result);

		// Debe ser el mismo Provider que al que pertenece el Item
		Assert.isTrue(principal.equals(result.getProvider()));

		return result;

	}

	public Collection<Item> searcherItems(final String searcher) {
		final Collection<Item> result = new ArrayList<Item>();
		final Collection<Item> all = this.findAll();

		final String s = searcher.toLowerCase();

		for (final Item i : all)
			if (i.getName().contains(s) || i.getDescription().contains(s) || i.getLink().contains(s))
				result.add(i);

		return result;
	}

}
