package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Clerk;


@Repository
public interface ClerkRepository extends JpaRepository<Clerk,Integer>{
	@Query("select cl from Clerk cl where cl.userAccount.id = ?1")
	Clerk findByUserAccountId(int userAccountId);

	
	@Query("select cl from Clerk cl where cl.deliveredOrders=(select max(cl.deliveredOrders) from Clerk cl)")
	Collection<Clerk> clerkWithMoreDeliveredOrders();

	@Query("select cl from Clerk cl where cl.deliveredOrders=(select min(cl.deliveredOrders) from Clerk cl)")
	Collection<Clerk> clerkWithLessDeliveredOrders();
}
