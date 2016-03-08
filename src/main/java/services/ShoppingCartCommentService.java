package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ShoppingCartCommentRepository;
import domain.Consumer;
import domain.ShoppingCart;
import domain.ShoppingCartComment;

@Service
@Transactional
public class ShoppingCartCommentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShoppingCartCommentRepository shoppingCartCommentRepository;
	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	// Constructors -----------------------------------------------------------

	public ShoppingCartCommentService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	public ShoppingCartComment create() {
		ShoppingCartComment result;

		result = new ShoppingCartComment();

		return result;
	}
	public Collection<ShoppingCartComment> findAll() {
		Collection<ShoppingCartComment> result;
		
		result = shoppingCartCommentRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	public ShoppingCartComment findOne(int ShoppingCartCommentId) {
		ShoppingCartComment result;

		result = shoppingCartCommentRepository.findOne(ShoppingCartCommentId);

		return result;
	}

	
	public void save(ShoppingCartComment actor) {
		Assert.notNull(actor);

		shoppingCartCommentRepository.save(actor);
	}

	public void delete(ShoppingCartComment scItem) {
		Assert.notNull(scItem);
		Assert.isTrue(scItem.getId() != 0);
		Assert.isTrue(shoppingCartCommentRepository.exists(scItem.getId()));
		Consumer consumer=consumerService.findByPrincipal();
		Assert.notNull(consumer);
		consumer.getShoppingCart().getComment().remove(scItem);
		consumerService.save(consumer);
		shoppingCartCommentRepository.delete(scItem);	
	}	
	public void addComment(ShoppingCartComment scItem,ShoppingCart sc){
		Assert.notNull(scItem);
		Assert.notNull(sc);
		shoppingCartCommentRepository.save(scItem);
		ShoppingCartComment scItem2=shoppingCartCommentRepository.findAll().get(shoppingCartCommentRepository.findAll().size()-1);
		
		Collection<ShoppingCartComment> lista=sc.getComment();
		
		lista.add(scItem2);
		sc.setComment(lista);
		
		shoppingCartService.save(sc);
	}

}
