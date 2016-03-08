package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Warehouse;


@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer>{
	@Query("select w from Warehouse w where w.id = ?1")
	Warehouse findById(int userAccountId);
	
}
