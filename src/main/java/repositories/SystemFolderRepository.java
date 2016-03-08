package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import domain.SystemFolder;

@Repository
public interface SystemFolderRepository extends JpaRepository<SystemFolder,Integer>{

	
}
