package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import domain.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

	
}
