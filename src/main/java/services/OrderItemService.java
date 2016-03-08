package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OrderItemRepository;

import domain.OrderItem;
@Service
@Transactional
public class OrderItemService {
//Supporting Services
	@Autowired
	private OrderItemRepository orderItemRepository;
//Constructor
	
	public OrderItemService(){
		super();
	}

	
	public OrderItem create(){
		OrderItem result;

		result = new OrderItem();		

		return result;
	}
	public void save(OrderItem orderItem) {
		Assert.notNull(orderItem);
		orderItemRepository.save(orderItem);
	}
	
	public List<OrderItem> findAll() {
		List<OrderItem> result;
		Assert.notNull(orderItemRepository);
		result = orderItemRepository.findAll();		
		Assert.notNull(result);
		return result;
	}
	

	public OrderItem findOne(int orderId) {
		OrderItem result;
		
		result = orderItemRepository.findOne(orderId);		

		return result;
	}
}
