package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import domain.Stores;

@Repository
public interface StoresRepository extends JpaRepository<Stores,Integer>{

	
}
