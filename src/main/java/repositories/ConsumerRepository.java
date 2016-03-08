package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Consumer;


@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,Integer>{
	
	@Query("select c from Consumer c where c.userAccount.id = ?1")
	Consumer findByUserAccountId(int Id);
	
	@Query("select c from Consumer c where c.id = ?1")
	Consumer findById(int Id);

	@Query("select c from Consumer c where c.order.size=(select max(c.order.size) from Consumer c)")
	Collection<Consumer> findConsumerWhoPlacedMoreOrders();


	@Query("select c from Consumer c where c.sumPrice= (select max(c.sumPrice) from Consumer c)")
	Collection<Consumer> findConsumerWhoSpentMoreMoney();
	
	@Query("select c from Consumer c where c.cancelledOrders=(select max(c.cancelledOrders) from Consumer c)")
	Collection<Consumer> consumerWithMoreCancelledOrders();

	@Query("select c from Consumer c where c.cancelledOrders=(select min(c.cancelledOrders) from Consumer c)")
	Collection<Consumer> consumerWithLessCancelledOrders();
}
