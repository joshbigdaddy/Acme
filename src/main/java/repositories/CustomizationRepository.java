package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customization;

@Repository
public interface CustomizationRepository extends JpaRepository<Customization,Integer>{

	@Query("select c from Customization c where c.id = ?1")
	Customization findById(int Id);
}
