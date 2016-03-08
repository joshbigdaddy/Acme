package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Plaba;


@Repository
public interface PlabaRepository extends JpaRepository<Plaba,Integer>{

	
}

