package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Clerk;
import domain.Consumer;
import domain.CreditCard;
import domain.Order;
import domain.OrderItem;
import domain.Plaba;
import domain.ShoppingCart;
import domain.ShoppingCartComment;
import domain.ShoppingCartItem;
import domain.Stores;
import domain.Warehouse;

import repositories.OrderRepository;
@Service
@Transactional
public class OrderService {



	// Managed repository -----------------------------------------------------
	@Autowired
	private OrderRepository orderRepository;
	


	// Supporting services ----------------------------------------------------
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ItemService itemService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ClerkService clerkService;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private ConsumerService consumerService;

	public Order create() {
		Order result;

		result = new Order();		

		return result;
	}
	public Order create(String ticker) {
		Order result;

		result = new Order(ticker);		

		return result;
	}
	
	public List<Order> findAll() {
		List<Order> result;

		Assert.notNull(orderRepository);
		result = orderRepository.findAll();		
		Assert.notNull(result);
		
		return result;
	}
	public Collection<Order> showAll() {
		Collection<Order> result;
		Assert.notNull(orderRepository);
		Assert.notNull(administratorService);
		Administrator admin=administratorService.findByPrincipal();
		Assert.notNull(admin);
		result = orderRepository.findAll();		
		Assert.notNull(result);
		
		return result;
	}

	public Order findOne(int orderId) {
		Order result;
		
		result = orderRepository.findOne(orderId);		

		return result;
	}
	
	public void save(Order order) {

		orderRepository.save(order);
	}	
	
	public void delete(Order order) {
		Assert.notNull(order);
		Assert.isTrue(order.getId() != 0);
		Assert.isTrue(orderRepository.exists(order.getId()));
		/*Collection<Clerk> clerks=clerkService.findAll();
		boolean res=true;
		for(Clerk c: clerks){
		if(c.getOrders().contains(order)) res=false;
		}
		Collection<Consumer> consumers=consumerService.findAll();
		for(Consumer con: consumers){
		if(con.getOrder().contains(order)) res=false;
		}
		Assert.isTrue(res);
		*/
		orderRepository.delete(order);
		
	}
	
		public Long ratioOrdersCancelledMonth(){
		Long result;
		if(orderRepository.ratioOfOrdersPlacementThisMonth()<1){
			result=1L;
		}else{
		result=orderRepository.ratioOfOrdersCancelledThisMonth()/orderRepository.ratioOfOrdersPlacementThisMonth();
		}
		Assert.notNull(result);
		return result;
		}
		public Order findByTicker(String ticker){
			Order o =null;
			o=orderRepository.findByTicker(ticker);
			return o;
		}

		public void createOrderByShoppingCart(CreditCard credit,String name,String address) {
			Consumer c= consumerService.findByPrincipal();
			ShoppingCart sc=c.getShoppingCart();
			Collection<ShoppingCartItem> items=sc.getItem();
			Collection<OrderItem> orderItems=new ArrayList<OrderItem>();
			//Collection<Warehouse> warehouses=warehouseService.findAll();
			for(ShoppingCartItem i:items){
				
				OrderItem oi=orderItemService.create();
				oi.setDescription(i.getDescription());
				oi.setName(i.getName());
				oi.setPicture(i.getPicture());
				oi.setPrice(i.getPrice());
				oi.setSku(i.getSku());
				oi.setTag(i.getTag());
				oi.setQuantity(i.getQuantity());
				orderItemService.save(oi);
				orderItems.add(orderItemService.findAll().get(orderItemService.findAll().size()-1));
				
			}
			Collection<ShoppingCartItem> scitems=shoppingCartService.getItems();
			List<ShoppingCartItem> auxiliar=new ArrayList<ShoppingCartItem>();
			for(ShoppingCartItem i:scitems){
				auxiliar.add(i);
			}
			
			/*for(int i=0;i<auxiliar.size();i++){
				int cantidadUsada=0;
				int cantidad=auxiliar.get(i).getQuantity();
				for(Warehouse w:warehouses){
					
					
					if(cantidadUsada<cantidad){
					for(Stores s:w.getStores()){
						if(s.getItem().getSku().equals(auxiliar.get(i).getSku())){
								warehouseService.changeCuantityOfStoredItem(w.getId(), s.getItem(), s.getUnits()-1);
						}
					}
				}else{
					break;
				}
				}
			}*/
			
			Assert.notNull(c);
		
	        Order o=create();
	        o.setTicker(generateTicker());
	        o.setPlacementMoment(new Date(System.currentTimeMillis()-1));
	        o.setConsumer(c);
	        o.setName(name);
	        o.setAddress(address);
	        o.setCreditCard(credit);
	        List<String> comments=new ArrayList<String>();
	        for(ShoppingCartComment s:c.getShoppingCart().getComment()){
	        	comments.add(s.getComment());
	        }
	        o.setComments(comments);
	        o.setOrderItems(orderItems);
	        orderRepository.save(o);
	        c.getOrder().add(findAll().get(findAll().size()-1));
	        
	       sc.getItem().clear();
	       sc.getComment().clear();
	       shoppingCartService.save(sc);
		}
		public void cancelOrder(Order order){
			Assert.notNull(consumerService);
			Consumer consumer=consumerService.findByPrincipal();
			Assert.notNull(consumer);
			boolean res=true;
			for(Clerk c:clerkService.findAll()){
				if(c.getOrders().contains(order)){
					res=false;
					break;
				}
			}
			Assert.isTrue(res);
			order.setCancelMoment(new Date(System.currentTimeMillis()-1));
			orderRepository.save(order);
		}

		public void assignCurrentClerk(Order order) {
			order=findOne(order.getId());
			order.setClerk(clerkService.findByPrincipal());
			orderRepository.save(order);
		
		}

		public String generateTicker() {
			boolean aux=false;
			String s="";
			double x;
			Order o;
			while(!aux){
			s="";
			for(int i=0;i<6;i++){
				x=Math.random();
				x=x*10;
				int e=(int) x;
				s+=e;
			}
			s+="-";
			for(int i=0;i<4;i++){
				x=Math.random();
				x=x*10;
				int e=(int) x;
				s+=e;
			}
			o=findByTicker(s);
			if(o==null){
				aux=true;
			}
		}
			return s;
		}
		public void withdrawItemsWarehouse(List<OrderItem> auxiliar){
			List<Warehouse> warehouses=warehouseService.findAll();
			boolean aux=true;
			String nombre="";
			List<Warehouse> warehousesUsadas=new ArrayList<Warehouse>();
			for(int i=0;i<auxiliar.size();i++){
				aux=true;
				if(nombre.equals("")||!nombre.equals(auxiliar.get(i).getName())){
					nombre=auxiliar.get(i).getName();
					warehousesUsadas.clear();
				}
				for(Warehouse w:warehouses){
				if(!warehousesUsadas.contains(w)){
					if(!aux){
						break;
					}
					for(Stores s:w.getStores()){
						if(s.getItem().getSku().equals(auxiliar.get(i).getSku())){
								warehouseService.changeCuantityOfStoredItem(w.getId(), s.getItem(), s.getUnits()-1);
								s.getItem().setUnitsSold(s.getItem().getUnitsSold()+1);
								itemService.save(s.getItem());
								warehousesUsadas.add(w);
								aux=false;
								break;
						}
					}
				}
				
				}
			}
		}
		public void usePlaba(Order order) {
			// TODO Auto-generated method stub
			List<Plaba> plabas=order.getConsumer().getPlaba();
			Plaba plaba=plabas.get(0);
			order.setSumPrice(order.getSumPrice()-plaba.getAmmount());
			save(order);
		}
		
}
