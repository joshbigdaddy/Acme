package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ShoppingCartItemRepository;
import domain.Consumer;
import domain.ShoppingCartItem;

@Service
@Transactional
public class ShoppingCartItemService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShoppingCartItemRepository shoppingCartItemRepository;	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private ConsumerService consumerService;
	// Constructors -----------------------------------------------------------

	public ShoppingCartItemService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	public ShoppingCartItem create() {
		ShoppingCartItem result;

		result = new ShoppingCartItem();

		return result;
	}
	public List<ShoppingCartItem> findAll() {
		List<ShoppingCartItem> result;
		
		result = shoppingCartItemRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	public ShoppingCartItem findOne(int ShoppingCartItemId) {
		ShoppingCartItem result;

		result = shoppingCartItemRepository.findOne(ShoppingCartItemId);

		return result;
	}

	
	public void save(ShoppingCartItem actor) {
		Assert.notNull(actor);

		shoppingCartItemRepository.save(actor);
	}

	public void delete(ShoppingCartItem scItem) {
		Assert.notNull(scItem);
		Assert.isTrue(scItem.getId() != 0);
		Assert.isTrue(shoppingCartItemRepository.exists(scItem.getId()));
		Consumer consumer=consumerService.findByPrincipal();
		Assert.notNull(consumer);
		consumer.getShoppingCart().getItem().remove(scItem);
		consumerService.save(consumer);
		shoppingCartItemRepository.delete(scItem);	
	}	

}
