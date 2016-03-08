package services;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ShoppingCartRepository;
import domain.Consumer;
import domain.Item;
import domain.ShoppingCart;
import domain.ShoppingCartItem;

@Service
@Transactional
public class ShoppingCartService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private ShoppingCartItemService shoppingCartItemService;
	
	// Constructors -----------------------------------------------------------

	public ShoppingCartService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	public ShoppingCart create() {
		ShoppingCart result;

		result = new ShoppingCart();

		return result;
	}
	public List<ShoppingCart> findAll() {
		List<ShoppingCart> result;
		
		result = shoppingCartRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	public ShoppingCart findOne(int ShoppingCartId) {
		ShoppingCart result;

		result = shoppingCartRepository.findById(ShoppingCartId);

		return result;
	}

	
	public void save(ShoppingCart actor) {
		Assert.notNull(actor);

		shoppingCartRepository.save(actor);
	}	

	// Other business methods -------------------------------------------------

	public Collection<ShoppingCartItem> getItems(){
		Consumer consumer;
		ShoppingCart sc;
		
		consumer=consumerService.findByPrincipal();
		Assert.notNull(consumer);
		sc=consumer.getShoppingCart();
		
		Collection<ShoppingCartItem> items= sc.getItem();
		
		return items;
	}
	public void addItem(Item item){
		Consumer consumer;
		ShoppingCart sc;
		consumer=consumerService.findByPrincipal();
		
		Assert.notNull(consumer);
		sc=consumer.getShoppingCart();
		boolean aux=false;
		for(ShoppingCartItem s:sc.getItem()){
			if(s.getSku().equals(item.getSku())){
				s.setQuantity(s.getQuantity()+1);
				aux=true;
				break;
			}
		}
		if(!aux){
			ShoppingCartItem oi=shoppingCartItemService.create();
			oi.setDescription(item.getDescription());
			oi.setName(item.getName());
			oi.setPicture(item.getPicture());
			oi.setPrice(item.getPrice());
			oi.setSku(item.getSku());
			oi.setTag(item.getTag());
			oi.setQuantity(1);
			shoppingCartItemService.save(oi);
			sc.getItem().add(shoppingCartItemService.findAll().get(shoppingCartItemService.findAll().size()-1));
		}
		shoppingCartRepository.save(sc);
	}

	public void modifyQuantity(Item item, Integer quantity){
		Consumer consumer;
		ShoppingCart sc;
		
		consumer=consumerService.findByPrincipal();
		Assert.notNull(consumer);
		sc=consumer.getShoppingCart();
		for(ShoppingCartItem s:sc.getItem()){
			if(s.getSku().equals(item.getSku())){
				s.setQuantity(quantity);
	
				break;
			}
		}
		shoppingCartRepository.save(sc);
	}
	public void removeItem(Item item){
		Consumer consumer;
		ShoppingCart sc;
		
		consumer=consumerService.findByPrincipal();
		ShoppingCartItem aux=null;
		Assert.notNull(consumer);
		sc=consumer.getShoppingCart();
		for(ShoppingCartItem s:sc.getItem()){
			if(s.getSku().equals(item.getSku())){
				aux=s;
				break;
			}
		}
		if(aux!=null){
			sc.getItem().remove(aux);
		}
		shoppingCartRepository.save(sc);
	}

	
}
