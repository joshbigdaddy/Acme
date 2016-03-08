package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	
	@Query("select count(o) from Order o where MONTH(o.cancelMoment)=MONTH(CURRENT_TIMESTAMP)")
	Long ratioOfOrdersCancelledThisMonth();
	@Query("select count(o) from Order o where MONTH(o.placementMoment)=MONTH(CURRENT_TIMESTAMP)")
	Long ratioOfOrdersPlacementThisMonth();
	
	@Query("select o from Order o where o.ticker = ?1")
	Order findByTicker(String ticker);

	
}
