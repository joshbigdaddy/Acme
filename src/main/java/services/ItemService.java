package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



import domain.Comment;
import domain.Item;
import domain.ShoppingCart;
import domain.Warehouse;

import repositories.ItemRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	
//Supporting Services
	@Autowired
	private CommentService commentService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private WarehouseService warehouseService;
//Constructor
	
	public ItemService(){
		super();
	}

	
	public Item create(){
		Item result;

		result = new Item();		

		return result;
	}
	
	public Collection<Item> findAll(){
		return itemRepository.findAll();
	}
	
	public Collection<Item> findAllButDeleted(){
		List<Item> notDeletedItems= new ArrayList<Item>();
		Collection<Item> items= itemRepository.findAll();
		for(Item i: items){
			if(!i.isDeleted()){
				notDeletedItems.add(i);
			}
		}
		
		return notDeletedItems;		
	}
	
	public Collection<Item> findAllDeleted(){
		List<Item> deletedItems= new ArrayList<Item>();
		Collection<Item> items= itemRepository.findAll();
		for(Item i: items){
			if(i.isDeleted()){
				deletedItems.add(i);
			}
		}
		
		return deletedItems;		
	}
	public Item findOne(int actorId) {
		Assert.isTrue(actorId != 0);
		
		Item result;

		result = itemRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}
	
	public Collection<Item> findOneBySearch(String search){
		Collection<Item> items = findAllButDeleted();
		List<Item> itemB=new ArrayList<Item>();
		for(Item item : items){
			if(item.getSku().contains(search) || item.getDescription().contains(search) ||item.getName().contains(search)  ){
				itemB.add(item);
			
			}
		}
		return itemB;
	}
	
	
	public void save(Item i){
		itemRepository.save(i);
	}
	public void delete(Item i){
		Boolean y = true;
		
		for(ShoppingCart cart : shoppingCartService.findAll()){
			if(cart.getItem().contains(i)){
				y=false;
			}
		}
		for(Warehouse ware : warehouseService.findAll()){
			if(ware.getItems().contains(i)){
				y=false;
			}
		}
		Assert.isTrue(y);
		i.setDeleted(true);
		itemRepository.save(i);
		
	}
	
	//Other bussines methods
	
	public Collection<Item> bestSelling(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Item> result;
		
		result = itemRepository.bestSellingItem();
		Assert.notNull(result);
		
		return result;
	}
	public Collection<Item> worstSelling(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Collection<Item> result;
		
		result = itemRepository.worstSellingItem();
		Assert.notNull(result);
		
		return result;
	}
	public Collection<Item> moreComments(){
		Collection<Item> result;
		
		result = itemRepository.itemWithMoreComments();
		Assert.notNull(result);
		
		return result;
	}
	public Collection<Item> groupByCategory(){
		Collection<Item> result;
		
		result = itemRepository.itemsGroupByCategory();
		Assert.notNull(result);
		
		return result;
		
		}
	public Collection<Comment> getListComments(Item item){
		
		Collection<Comment> comments=item.getComment();
		return comments;
	}
	public void createComment(Item item){
		Assert.notNull(item);
		Comment com= commentService.create();
		Collection<Comment> c=item.getComment();
		c.add(com);
		item.setComment(c);
		itemRepository.save(item);
	}


	public Item findOneBySKU(String sku) {
		Collection<Item> items = findAll();
		Item itemB=null;
		for(Item item : items){
			if(item.getSku().contains(sku) ){
				itemB=item;
			break;
			}
		}
		return itemB;
	}
}
